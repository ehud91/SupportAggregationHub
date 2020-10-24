# SupportAggregationHub

This project is an API application that should handle the complaints based on common denominators.

This Maven project is based on Spring boot framework in Java.
The project is based and require require the CasesApi Project in order to bring the data for all cases.

## Getting Started

### Requirements
- [Git](https://git-scm.com/downloads)
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Clone

To get started you can simply clone this repository using git:
```shell
$ git clone https://github.com/ehud91/SupportAggregationHub.git
$ cd SupportAggregationHub
```
### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.supportaggregationapi.execute.Application` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse / IntelliJ IDEA
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/ehud91/SupportAggregationHub.git
$ cd SupportAggregationHub
$ mvn spring-boot:run
```

### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/ehud91/SupportAggregationHub.git
$ cd SupportAggregationHub
$ mvn package -DskipTests
$ java -jar target/SupportAggregationApi-1.0-SNAPSHOT.jar
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **tasklist** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command


## Configure your own application properties

### /src/main/resources/application.properties

### Port configurations

Configure your own port on your machine

```properties
server.port=1030
```

### Second project CasesApi url configurations

Configure the url + port of the CasesApi project, After you finished the installation of the CasesApi project.

```properties
# Service AggregationHub service

service.api.aggregationhub-url = http://localhost:1020/api/getAllCasesApi
```

### Validation for requests from an external CRM system

This property validate requests from an external CRM system.
You may change the validation properties as you want

```properties
# External CRM system properties

crm.api.request-appid = APP001
crm.api.request-appkey = 15nXqSDXnNeaIEFQSSDXkNeZ16DXodeV16TXmSDXoteb16nXmdeVISEh
```

### Logs configurations

### /src/main/resources/logback-spring.xml

These configurations are based on the project name, and they should locate the logs file
in the /log folder inside the project.
You can change both the logs name and location as you want under the:
```xml
<property name="LOGS" value="./logs" />
```
and -
```xml
<file>...</file>
```
and - 
```xml
<fileNamePattern>...</fileNamePattern>
```

```xml
<property name="LOGS" value="./logs" />

<appender name="RollingFile"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOGS}/SupportAggregationApi.log</file>
        <encoder
                class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <Pattern>%d %p %C{1.} [%t] %m%n</Pattern>
        </encoder>

        <rollingPolicy
                class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily and when the file reaches 10 MegaBytes -->
            <fileNamePattern>${LOGS}/archived/SupportAggregationApi-%d{yyyy-MM-dd}.%i.log
            </fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy
                    class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
    </appender>
```

