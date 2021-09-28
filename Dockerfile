FROM python:3.9-slim-buster
ENV PYTHONUNBUFFERED 1

WORKDIR /app
COPY requirements.txt .
RUN pip3 install -r requirements.txt

EXPOSE 8000
