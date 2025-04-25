# ADempiere Template

This project allows define a new project using gRPC with ADempiere. Is very good for create your own service like push Orders, attendance and other business case.

## Requirements

The ADempiere template is a service to expose ADempiere as gRPC service with a little functionality of ADempiere:
 
 - Create Entity
 - Update Entity
 - Delete Entity
 - Get Entity
 
Since the ADempiere dependency is vital for this project is high recommended that the you are sure that of project [adempiere-jwt-token](https://github.com/adempiere/adempiere-jwt-token) is installed and the setup is runned in ADempiere Database.

## Run it from Gradle

```Shell
gradle run --args="resources/env.yaml"
```


## Some Notes

For Token validation is used [JWT](https://www.viralpatel.net/java-create-validate-jwt-token/)

## Run with Docker

```Shell
docker pull openls/adempiere-grpc-template-service:alpine
```

### Minimal Docker Requirements
To use this Docker image you must have your Docker engine version greater than or equal to 3.0.

### Environment variables
 * `DB_TYPE`: Database Type (Supported `Oracle` and `PostgreSQL`). Default `PostgreSQL`.
 * `DB_HOST`: Hostname for data base server. Default: `localhost`.
 * `DB_PORT`: Port used by data base server. Default: `5432`.
 * `DB_NAME`: Database name that adempiere-grpc-template-service will use to connect with the database. Default: `adempiere`.
 * `DB_USER`: Database user that adempiere-grpc-template-service will use to connect with the database. Default: `adempiere`.
 * `DB_PASSWORD`: Database password that Adempiere-Backend will use to connect with the database. Default: `adempiere`.
 * `IDLE_TIMEOUT`: It sets the maximum time a connection can sit around without being used before it gets closed to free up resources. Default: `300`.
 * `MINIMUM_IDLE`: It sets the minimum number of connections that should be kept open and ready to use, even if they're not currently being used. This helps improve performance by reducing the time it takes to get a connection. Default: `1`.
 * `MAXIMUM_POOL_SIZE`: It sets the maximum number of connections that can be open at the same time. This helps prevent the pool from getting too big and using up too much memory. Default: `10`.
 * `CONNECTION_TIMEOUT`: it sets the maximum time HikariCP will wait to get a connection from the pool before giving up and throwing an error. Default: `5000`.
 * `MAXIMUM_LIFETIME`: It sets the maximum amount of time a connection can stay open in the pool before it's automatically closed. This helps keep the pool clean and prevents problems. Default: `6000`.
 * `KEEPALIVE_TIME`: It sets a test query that HikariCP will run on connections to make sure they're still working properly. Default: `360000`.
 * `CONNECTION_TEST_QUERY`: It sets how often HikariCP will check if a connection is still working properly. This helps prevent problems with connections that might become inactive. Default: `SELECT 1`.
 * `SERVER_PORT`: Port to access adempiere-grpc-template-service from outside of the container. Default: `50059`.
 * `SERVER_LOG_LEVEL`: Log Level. Default: `WARNING`.
 * `JAVA_OPTIONS`: Custom settings to the Java Virtual Machine (JVM). Default: `-Xms64M -Xmx1512M`.
 * `TZ`: (Time Zone) Indicates the time zone to set in the nginx-based container, the default value is `America/Caracas` (UTC -4:00).

You can download the last image from docker hub, just run the follow command:

```Shell
docker run -d -p 50059:50059 --name adempiere-grpc-template-service -e DB_HOST="localhost" -e DB_PORT=5432 -e DB_NAME="adempiere" -e DB_USER="adempiere" -e DB_PASSWORD="adempiere" openls/adempiere-grpc-template-service:alpine
```

See all images [here](https://hub.docker.com/r/openls/adempiere-grpc-template-service)

## Run with Docker Compose

You can also run it with `docker compose` for develop enviroment. Note that this is a easy way for start the service with PostgreSQL and template.

### Requirements

- [Docker Compose v2.16.0 or later](https://docs.docker.com/compose/install/linux/)

```Shell
docker compose version
Docker Compose version v2.16.0
```

## Run it

Just go to `docker-compose` folder and run it

```Shell
cd docker-compose
```

```Shell
docker compose up
```

### Some Variables

You can change variables editing the `.env` file. Note that this file have a minimal example.

## What else?

Just rename all word `template` to `your-own-name`.
