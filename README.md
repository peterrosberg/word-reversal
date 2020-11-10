# Word reversal

## Requirements

You need to have the following installed:
Java JDK version 11

## Setup

## Running locally

## Deploying

To deploy to Heroku, first make sure you have an account. 
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
./gradlew clean build
```

Finally build and release the container to Heroku using these commands
```
heroku container:push web
heroku container:release web
```

Now you can open the app in tour broser using:
```
heroku open
```

For trouble shooting see the [Heroku documentation](https://devcenter.heroku.com/articles/container-registry-and-runtime)
