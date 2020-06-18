# Timely Batch

## Running the batch job

You can run the job at any time on your own machine with the `bootRun` command:

```
NYT_API_KEY={your key here} ./gradlew bootRun
```

To run with Docker, first add your API keys to the `docker.env` file at the root of this project. Then run the included Shell script to build and run the Docker image:

```
./run-docker.sh
```

## Troubleshooting

**Constructor threw exception; nested exception is java.lang.IllegalStateException: Missing NYT_API_KEY environment variable; cannot call New York Times API.**

You must provide the batch job a New York Times Newswire API key. If running the project via `bootRun`, send your key in on the command line, via the example above. If running via Docker, add your key to the `docker.env` file.