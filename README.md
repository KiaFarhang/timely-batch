# Timely Batch

## Running the batch job

You can run the job at any time on your own machine with the `bootRun` command:

```
NYT_API_KEY={your key here} ./gradlew bootRun
```

To run with Docker, first build the code to create a JAR file:

```
./gradlew build
```

Then build the Docker image:

```
docker build --build-arg JAR_FILE=build/libs/*.jar -t kiafarhang/timely-batch-docker .
```

And add your API keys to the `docker.env` file at the root of this project.

You can now run the image:

```
docker run -p 8080:8080 --env-file ./docker.env kiafarhang/timely-batch-docker
```