#!/bin/bash

SERVICE_BASE_URL="$1"
if [ -z "$SERVICE_BASE_URL" ]; then
  echo "No service base URL provided"
  exit 1
fi

SIMULATION_CLASS="$2"
if [ -z "$SIMULATION_CLASS" ]; then
  echo "No simulation class provided"
  exit 1
fi

./mvnw gatling:test \
  -D"baseUrl"="$SERVICE_BASE_URL" \
  -D"gatling.simulationClass"="$SIMULATION_CLASS"
