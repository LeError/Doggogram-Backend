# Doggogram Backend

[![CodeFactor](https://www.codefactor.io/repository/github/leerror/doggogram-backend/badge)](https://www.codefactor.io/repository/github/leerror/doggogram-backend)

This repository holds the code for the project for the lecture *Webprogrammierung* at the Baden-Wuerttemberg Cooperative State University (DHBW) Ravensburg.

tbd desc software

## Getting Started

These instructions will explain how to get this project into IntelliJ IDEA

### Prerequisites

Install a IDE if your choice in my case [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Installation for Development

* In the IntelliJ IDEA splash screen select Check out from Version Control
* In the dropdown menu select git 
* In the dialog that opend enter the url of this reposetory as seen below and choose clone
* In the upcoming dialogs just press next and your done
* Apply that you want to Import the Project via Pom

```
https://github.com/LeError/Doggogram-Backend
```

## Documentation

A list of Endpoints that the Backend provides can be found in [List.md](files/doc/endpoints/List.md). In the [doc](files/doc/endpoints/) directory can also detail documentation to the different controller be found and the details of the corrosponding endpoints.

## Test Environment

* The test environment is a Debian (x64) Linux Server
  * 2 CPU Cores
  * 2 GB RAM
  * 25 GB Storage
  * 1 IP-Adress: http://88.214.57.214/
* The Application is Configured as systemd Service
  * The configuration script can be found in the [Service File](files/debian/doggogramsvc.service)
  * The Application is able to run on different environments even on cloud PaaS (when cloud dependencies are added)
* The Database that is used in the test scenario is MariaDB
  * Dialect: MySQL
  * Hosted on the same test environment
* The [Frontend](https://github.com/xSpeed1337/Doggogram-Frontend) is also hosted on the the test evirnonment 
  * Develeoped with HTML, CSS and JavaScript
  * It is exposed to the Internet via a Apache installation with PHP configured
  
## Used Technologies

* Java
* HTTP-Protocol
* MySQL
* JSON
* XML
* Maven
* Embedded Tomcat

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
  * [Spring Security](https://github.com/spring-projects/spring-security)
  * [Spring Boot Starter Web](https://github.com/spring-projects/spring-boot)
  * [Spring Boot Starter Test](https://github.com/spring-projects/spring-boot)
  * [Spring Boot Starter Security](https://github.com/spring-projects/spring-boot)
  * [Spring Boot Starter Data Jpa](https://github.com/spring-projects/spring-boot)
* [Apache commons IO](https://commons.apache.org/proper/commons-io/)
* [Google Guava](https://github.com/google/guava)
* [JJWT](https://github.com/nzoudy/JSON-Web-Token)
* [MariaDB Java Client](https://github.com/mariadb-corporation/mariadb-connector-j)
* [Tomcat Embed Core](https://github.com/apache/tomcat)
* [Project Lombok](https://github.com/rzwitserloot/lombok)
* [Mapstruct](https://github.com/mapstruct/mapstruct)

### Maven Plugins

* [Maven Surefire](https://github.com/apache/maven-surefire)
* [Maven Compiler Plugin](https://github.com/apache/maven-plugins)
* [Maven Release Plugin](https://github.com/mengshijian/mavenreleaseplugin)
* [Spring Boot Maven Plugin](https://github.com/spring-projects/spring-boot)

## Authors

* **Moritz Bürkle** - *Implementation / Planning / Concept* - [moritzbuerkle](https://github.com/moritzbuerkle)
* **Lukas Fink** - *Implementation / Planning / Concept* - [xSpeed1337](https://github.com/xSpeed1337)
* **Malik Press** - *Implementation / Planning / Concept* - [W4W3](https://github.com/W4W3)
* **Robin Herder** - *Implementation / Planning / Concept* - [LeError](https://github.com/LeError)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Was created as a Baden-Wuerttemberg Cooperative State University (DHBW) Ravensburg project for the lecture *Webprogrammierung*
* Thanks to my teammates Moritz, Lukas and Malik 
