# Word reversal

## Requirements

To develop and run locally you will need:
* Java JDK version 11 //TODO or 14?
* Node.js version 14.13.0 or later
* Npm version 6.14.8 or later
* Gradle
* Docker
* "a modern browser"

For deployment you also need:
* [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)

## Setup

## Test

### Unit and integration tests
Run the test suite from your terminal by:
```
./gradlew test 
```

### Selenium test

First you need to install the selenium chrome driver on your system. 
Instructions for that is available [here](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)

## Running locally

You have two options running locally, development mode or using docker.

### Run local dev environment

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

To deploy to Heroku, first make sure you have [an account](https://signup.heroku.com/). 
When you do you can authenticate in your terminal by running:
```
heroku container:login
```

Then create an app using:
```
heroku create
```

Before you deploy to Heroku you need to build the application jar locally
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
