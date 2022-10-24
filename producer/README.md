# producer Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Setting Environment Variables

```MESSAGE_PERIOD``` - Regulates how often in milliseconds a message is produced to the topic. Default is ```5000```

```MQTT_BROKER_URL``` - URL of MQTT broker. Default is ```tcp://broker.hivemq.com:1883```

```MQTT_TOPIC``` - Topic to write to. Default is ```/telemetry/GBENGA```

```MQTT_CONSUMER_ON``` - Turn the internal consumer ON to consume and view messages. The default is ```true``` in dev mode, ```false``` otherwise

The BPM range in the generated payload is ```50-180```. To modify the range, set the following variables

```VITAL_SIGNS_MIN_BPM``` - Set the lower bound

```VITAL_SIGNS_MAX_BPM``` - Set the upper bound

In OpenShift/Kubernetes, these can be set via environment variables on the ```Deployment``` or ```DeploymentConfig``` directly or mounted by configMap

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/producer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
