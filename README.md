# Taxi Data Store

This sample project receives taxi data through REST API and store it in Database (MongoDB for now) 

Full documentation of REST API is in file restapi/src/main/resources/taxi-api.yaml that can be opened/edited using Swagger editor (http://editor.swagger.io/)

The project has two models
restapi : contains the definition of the REST API
restservice : contains the REST API implementation

To run the service, first we build the api from the model 'restapi':

```cd restapi```

```mvn clean install```

Then we build & run service the model 'restservice':

```cd ../restservice```

```mvn clean package```

```mvn clean install```

```java -jar target\taxi-restservice-[VERSION].jar```