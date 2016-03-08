Spring Demo-Application: Nobody's Perfect
=========================================

This Spring Boot Demo Application implements a "[Nobody's Perfect](https://www.ravensburger.de/shop/spiele/erwachsenenspiele/nobody-is-perfect-27225/index.html)" REST API. The goal of the demo application is to provide best-practices for developing RESTful web services with Spring-Boot.



Prerequisites
-------------

The application expects a MongoDB server that is provided locally on the development machine. You can set this up using a `Vagrantfile` like the one that is provided with this repository. From the root of the project directory, run `vagrant up`.




Starting the Application
------------------------

For starting the application, simply run `mvn spring-boot:run`



Running the Tests
-----------------

For running the tests, simply run `mvn test`



API Documentation
-----------------

After starting the application, an API documentation is provided via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)



Further Resources
=================

RESTful Services with Spring
----------------------------

* [Tutorial for writing REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)



Testing Spring Applications
---------------------------

* [REST Web Service Testing with Spring](http://blog.zenika.com/2013/01/15/rest-web-services-testing-with-spring-mvc/)
* [Integration Testing of Spring MVC Applications - JSON Path](http://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-write-clean-assertions-with-jsonpath/)
* [Testing REST web services using spring (Video)](http://www.leveluplunch.com/java/tutorials/030-testing-spring-rest-webservice-controllers/)
* [Spring Boot Documentation on Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)



Spring and MongoDB
------------------

* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [The Mongo Shell](https://docs.mongodb.org/manual/mongo/)



Debugging Spring
----------------

* []



Swagger
-------

* [Integrating Swagger into a Spring Boot RESTful Webservice with Springfox](http://www.hascode.com/2015/07/integrating-swagger-into-a-spring-boot-restful-webservice-with-springfox/)