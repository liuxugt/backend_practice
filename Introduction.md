## Introduction
This repository is a backend server built with Java, SpringBoot and Mybatis

## Prerequisites

### Database
Install mysql server and create a database called pomodoro_db. Create three tables in the database. The code could be found in 
```
src/main/resources/create_database.sql
```

The database configuration is in the ```src/main/resources/configuration.xml```, change the password into your own password.

### Gradle
The gradle is required to compile and build the repository. run `gradle -v` to test whether the installation is sucessful after install it through internet.

### Java
Make sure you can run java with JDK in your environment. Mybatis is also required to download.

## Run the server
after clone the repo into your local device and configure the local database and configuration file. Then run the following code in terminal in root directory to run the server in development mode.

```
./gradlew build -x test && java -jar build/libs/pomodoro-time-tracker-1.0.0.jar --mode=development
```

change `development` to `production` to run the server in production mode.