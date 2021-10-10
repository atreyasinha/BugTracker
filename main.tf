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
    region = "us-west1"
    zone = "us-west1-a"
}
