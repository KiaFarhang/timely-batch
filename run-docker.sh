#!/bin/bash
# Runs the Timely batch job via Docker container.

echo -e "Running Timely batch job\n"

# Delete any existing built JARs of the project
echo -e "Deleting any existing built versions of the project JAR...\n"
rm build/libs/*.jar

echo -e "Building the project...\n"
./gradlew build

echo -e "Running the project...\n"
docker-compose up --build