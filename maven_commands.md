# Maven Cheat Sheet

## Maven

| Command                                              | Description                                               |
|------------------------------------------------------|-----------------------------------------------------------|
| `mvn clean spring-boot:run`                          | Start Spring Boot application                             |
| `nohup mvn clean spring-boot:run &> output.log &`    | Start Spring Boot application in background, log to file  |
| `mvn dependency-check:check`                         | Run dependency check for vulnerabilities                  |
| `mvn clean install`                                  | Run full Maven build lifecycle (clean, compile, test, package, install) |
| `mvn clean install -DskipTests`                      | Run full Maven build, skipping tests                      |

---