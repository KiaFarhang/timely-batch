#!/bin/bash
# Runs the Timely batch job via Docker container.

echo -e "Running Timely batch job\n"

# Delete any existing built JARs of the project
echo -e "Deleting any existing built versions of the project JAR...\n"
rm build/libs/*.jar

echo -e "Building the project...\n"
./gradlew build

echo -e "Building a Docker image to run the code...\n"
docker build --build-arg JAR_FILE=build/libs/*.jar -t kiafarhang/timely-batch-docker .

echo -e "Running the Docker image...\n"
docker run -p 8080:8080 --env-file ./docker.env kiafarhang/timely-batch-docker