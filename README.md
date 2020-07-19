# AngelHack - Server
[angel hack day 링크](https://angelhackseoul.kr/)


## Version
- Maven 3.6.3
- Java 11
- Spring boot 2.3.1
- MySQL 8.0.xx


## Installation
### Clone the project
```
https://github.com/Team-Pork-and-Rice-Soupp/angelhack-server.git
```
### Project Settings
properties Setting
````
spring.datasource.url=jdbc:mysql://localhost:3306/angelhack?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username={{USER NAME}}
spring.datasource.password={{DB PASSWORD}}
````

DB Setting
```mysql
CREATE DATABASE angelhack;
```

## Build
```
mvn -f {project path} clean install -DskipTests
```

## Usage
```
java -jar {project path}/target/angelhack-0.0.1-SNAPSHOT.jar
```