terraform {
    required_providers {
        google = {
            source = "hashicorp/google"
            version = "3.5.0"
        }
    }
}

provider "google" {
    project = "bug-tracker-328604"
    credentials = "${file("bug-tracker-sa-credentials.json")}"
    region = "us-west1"
    zone = "us-west1-a"
}

resource "google_project_service" "run_api" {
    service = "run.googleapis.com"

    disable_on_destroy = true
}

resource "google_cloud_run_service" "bug_tracker_deploy" {
    name = "b-t-deploy"
    location = "us-west1"

    template {
        spec {
            containers {
                image = "gcr.io/bug-tracker-328604/b-t-registry:latest"
            }
        }
    }

    traffic {
        percent         = 100
        latest_revision = true
    }

    depends_on = [google_project_service.run_api]
}

data "google_iam_policy" "noauth" {
    binding {
        role = "roles/run.invoker"
        members = [
            "allUsers",
        ]
    }
}

resource "google_cloud_run_service_iam_policy" "noauth" {
    location    = google_cloud_run_service.bug_tracker_deploy.location
    project     = google_cloud_run_service.bug_tracker_deploy.project
    service     = google_cloud_run_service.bug_tracker_deploy.name

    policy_data = data.google_iam_policy.noauth.policy_data
}