# Doggogram Backend

[![CodeFactor](https://www.codefactor.io/repository/github/leerror/doggogram-backend/badge)](https://www.codefactor.io/repository/github/leerror/doggogram-backend)

This repository holds the code for the project for the lecture *Webprogrammierung* at the Baden-Wuerttemberg Cooperative State University (DHBW) Ravensburg.

tbd desc software

## Getting Started

These instructions will explain how to get this project into IntelliJ IDEA

### Prerequisites

Install a IDE if your choice in my case [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Installing

* In the IntelliJ IDEA splash screen select Check out from Version Control
* In the dropdown menu select git 
* In the dialog that opend enter the url of this reposetory as seen below and choose clone
* In the upcoming dialogs just press next and your done
* Apply that you want to Import the Project via Pom

```
https://github.com/LeError/Doggogram-Backend
```

## Test Environment

* The test environment is a Debian (x64) Linux Server
  * 2 CPU Cores
  * 2 GB RAM
  * 25 GB Storage
  * 1 IP-Adress: http://88.214.57.214/
* The Application is Configured as systemd Service
  * The configuration script can be found in the [Service File](files/debian/doggogramsvc.service)
  * The Application is able to run on differen environments even on cloud PaaS (when cloud dependencies are added)
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
* [Apache commons IO](https://commons.apache.org/proper/commons-io/)
* [Google Guava](https://github.com/google/guava)
* [JJWT](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)
* [MariaDB Java Client](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client)

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
