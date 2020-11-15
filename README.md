# Word reversal

This is a web app for reversing words in sentences. 

It features:
* React/redux Frontend
* Kotlin Spring boot backend
* Docker container for deployment
* Configuration for deploying on Heroku
* H2 in memory database for running locally and PostgreSQL for deployment environment

## Requirements

To develop and run locally you will need:
* Java JDK version 11
* Node.js version 14.13.0 or later
* Npm version 6.14.8 or later
* Gradle
* [Docker](https://www.docker.com/get-started)
* [Chrome web browser](https://www.google.com/chrome/)

For deployment you also need:
* [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)

## Setup

Install the required software for developing. Optionally install the deployment requirements if you want to
do that as well. Download the repository to a local directory and from that directory run

```
cd frontend
npm install
```

## Test

### Unit and integration tests
Run the test suite from your terminal by:
```
./gradlew test 
```

### Selenium test

First you need to install the selenium chrome driver on your system. 
Instructions for that is available [here](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)

To execute the test using gradle run from the project root:
```
./gradlew :selenium:test --tests "com.wordsmith.wordreversal.SeleniumTest.runTest"
```

## Running locally

You have two options running locally, development mode or using docker. When you run locally, the backend will 
automatically start up a fresh H2 database in memory each time, giving you a clean slate during development.

### Run local dev environment

Running locally is the best way to test development changes instantly. Locally you will need to start the
server and the frontend separately, on different ports, but the frontend will proxy requests to the backend
so that they can run as one anyway (*see the proxy setting in [package.json](./frontend/package.json)*).

To start the backend, simply trigger the bootRun gradle target. Either from the IDEA or with
```
./gradlew bootRun
```

To start the frontend, run npm start from the frontend folder
```
cd frontend
npm start
```

Now you can view the application in your browser by going to http://localhost:3000

### Running with Docker

To run with docker, first make sure you build the jar:
```
./gradlew clean bootJar
```

Then build the docker image:
```
docker build .
```

Finally you can run your docker image with the following command.
You get the container id as output on the last line of the previous command.

```
docker run --env PORT=8080 -p 8080:8080 <docker-container-id>
```
What happens here is that we set the environment variable PORT to tell spring which
port to run on (this is needed by Heroku), and then we map that port to 8080 on the outside. 
You can now view the app by going to http://localhost:8080 in your browser.

## Deploying

### Creating your Heroku environment
To deploy to Heroku, first make sure you have [an account](https://signup.heroku.com/). 
When you do you can authenticate in your terminal by running:
```
heroku container:login
```

Then create an app using:
```
heroku create
```

You also need to create a PostgreSQL database connected to your app, specifying the plan name
that you are using. Example way of doing that with the plan *hobby-dev*:
```
heroku addons:create heroku-postgresql:hobby-dev
```

Finally you should set an environment variable to tell the app which environment it is running.
This will be used to determine the spring profile on start. The app is configured for running in heroku
with the stage profile. Set it as follows:
```
heroku config:set ENVIRONMENT=stage
```

### Deploy the app

The app is set up do be deployed using docker for maximum portability. Before you deploy to Heroku you need to build the application jar locally
```
./gradlew clean bootJar
```

Finally build and release the container to Heroku using these commands. Note that this will build the docker container as well, so there is no need to do that separately.
```
heroku container:push web
heroku container:release web
```

Now you can open the app in your broser using:
```
heroku open
```

For trouble shooting see the [Heroku documentation](https://devcenter.heroku.com/articles/container-registry-and-runtime)
