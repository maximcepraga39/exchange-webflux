## Exchange service

### Gradle build
```shell
gradle clean build
```

### Docker build and run
```shell
docker compose build
docker compose up -d
```

### Run Flyway migration(only when postgres container is running)
```shell
gradle exchange-db:flywayMigrate
```

### Exchange app logs
```shell
docker compose logs echange-app -f
```
