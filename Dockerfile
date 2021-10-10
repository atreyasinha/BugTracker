FROM python:3.9-slim-buster
ENV PYTHONUNBUFFERED 1

WORKDIR /app
COPY . .
RUN pip3 install -r requirements.txt
