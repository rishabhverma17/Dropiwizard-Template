
# Dropwizard Microservice Template (Gradle)

This is a Dropwizard microservice template. Just fork it and start building over it.

### Features present
- Configuration reading from Environment variable.
- Mongo's DAO layer implemented for demo.
- Demo resource created.
- Spotless applied.

### Needs to be added
- dockerize.gradle before making it into production.

### Requirements to run
- Setup Mongo
    - Create DB demoMongoDB
    - Create Collection employees
- Build command

```bash
./gradlew spotlessApply clean build
```
- Run command

```bash
./gradlew spotlessApply clean run
```
