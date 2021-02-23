# Kotlin Prototype Project (Karibou)

## Setup

Java 14 or higher required

* `make db/start` to create docker container and start local database
* `make boot/run` to start the Spring app on port 9080
* `make db/migrate` to add the seeded data to the database

Other Commands

* `make build` task to designate assembling all outputs and running all checks
* `make check` run all verification tasks such as tests and linting
* `make lint` to run the [ktlint](https://ktlint.github.io/) linter
* `make format` to automatically format the code based upon [ktlint](https://ktlint.github.io/) rules
* `make clean` clear the contents of the build directory
* `make dependencies` listing of all dependencies used
* `make db/stop` to stop and remove docker container and local database

##### Documentation

* docs: http://localhost:9080/api/docs
    * yaml: http://localhost:9080/api/docs.yaml
* swagger: http://localhost:9080/api/swagger

## Database

Tool Tip: As of MySQL 8 you can use two new UUID functions

* BIN_TO_UUID: `SELECT BIN_TO_UUID(id) AS uuid FROM customer;`
* UUID_TO_BIN: `INSERT INTO customer (id) VALUES (UUID_TO_BIN('3f06af63-a93c-11e4-9797-00505690773f'));`

## Logging

Log level is configurable in `application.properties` by changing the value of `logging.level.root` to the desired
logging level. The options are: `TRACE, DEBUG, INFO, WARN, ERROR` in ascending order of severity.

## Architecture

##### Current Architecture Concept

* **adapters**
    * **persistence**
        * dao
        * entity
        * mapper
        * repository
    * **web**
        * api
            * contract
                * mapper
                * request
                * response
            * controller
            * exception
* **common**
* **core**
    * **application**
        * port
            * ingress
            * egress
        * service
        * usecase
    * **domain**
        * exception
        * model
        * validator
* **infrastructure**
    * migration
    
##### C4 Model Example

 * [CGB C4 Model](https://app.diagrams.net/#G1kmY93b2wZM0qWf6pklY4VxtE0Dr_MtX6) - Here's an example C4 Model that was put together for the CGB project.  The Component view reflects the architecture in this prototype project.

## Tools

* [Generate DTO](https://kt.academy/generate) - Helpful tool for generating code for mapping between objects

## Resources

#### Architecture Resources

* Architecture Designs & Patterns
    * [Hexagonal Architecture w/ Java & Spring Boot](https://reflectoring.io/spring-hexagonal/)
        * [DDD, Hexagonal, Onion, Clean, CQRS, … How I put it all together](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/)
        * [Domain-Driven Design and the Hexagonal Architecture](https://vaadin.com/learn/tutorials/ddd/ddd_and_hexagonal)
        * [Ready for changes with Hexagonal Architecture](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749)
    * [Hexagonal Architecture on Spring Boot](https://jivimberg.io/blog/2020/02/01/hexagonal-architecture-on-spring-boot/)
    * [Clean Architecture w/ Java 11](https://medium.com/slalom-build/clean-architecture-with-java-11-f78bba431041)
    * [Clean Architecture w/ Java & Spring Boot](https://medium.com/swlh/clean-architecture-java-spring-fea51e26e00)
    * [Should You Start With A Monolith or Microservices?](https://nordicapis.com/should-you-start-with-a-monolith-or-microservices/)
    * GitHub Examples
        * [Java & Spring Boot](https://github.com/thombergs/buckpal)
        * [Kotlin & Spring Boot](https://github.com/dustinsand/hex-arch-kotlin-spring-boot)
        * [Kotlin Microservices Example](https://github.com/rkudryashov/microservices-example)
    * Video Tutorials/Presentations
        * [GOTO 2019: "Good Enough" Architecture](https://www.youtube.com/watch?v=PzEox3szeRc)
        * [GOTO 2020: When To Use Microservices (And When Not To!)](https://www.youtube.com/watch?v=GBTdnfD6s5Q&t=500s)
        * [Spring I/O 2019: Clean Architecture with Spring](https://www.youtube.com/watch?v=cPH5AiqLQTo)
* Security/Authentication
    * [A Quick Guide to Using Keycloak with Spring Boot](https://www.baeldung.com/spring-boot-keycloak)
    * Video Tutorials/Presentations
        * [Spring I/O 2019: How to secure your Spring apps with Keycloak](https://www.youtube.com/watch?v=KrOd5wIkqls)
* Multi-Module
    * [Building a Multi-Module Spring Boot Application with Gradle](https://reflectoring.io/spring-boot-gradle-multi-module/)
    * [How to Create a Multi-Module Spring Boot Project using Gradle’s Kotlin DSL](https://kotlindays.com/2019/12/06/multi-module-spring-boot-in-kotlin-dsl/index.html)
* Multi-Tenancy 
    * [Multi-Tenancy Implementation using Spring Boot](https://medium.com/swlh/multi-tenancy-implementation-using-spring-boot-hibernate-6a8e3ecb251a)
    * Video Tutorials/Presentations
        * [GOTO 2020: SaaS Deep Dive: Designing and Building Multi-Tenant Solutions](https://www.youtube.com/watch?v=joz0DoSQDNw&t=1s)
        * [Multi-tenant architecture in 20 minutes](https://www.youtube.com/watch?v=0N4KknY_zdU&t=1s)
        * [AWS re:Invent 2019: SaaS tenant isolation patterns (ARC372-P)](https://www.youtube.com/watch?v=fuDZq-EspNA)

#### Java & Spring Resources

* [Spring](https://spring.io/)
    * [Spring Initializr](https://start.spring.io/)
    * [Spring Boot](https://spring.io/projects/spring-boot)
        * [Building REST services w/ Spring](https://spring.io/guides/tutorials/rest/)
    * [Spring REST Docs](https://spring.io/projects/spring-restdocs#overview)
        * [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)
    * [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
        * [A Guide to JPA with Spring](https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa)
        * [Introduction to Spring Data JPA](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)
        * [Simplify the DAO with Spring and Java Generics](https://www.baeldung.com/simplifying-the-data-access-layer-with-spring-and-java-generics)
        * [Boost the performance of your Spring Data JPA application](https://blog.ippon.tech/boost-the-performance-of-your-spring-data-jpa-application/)
        * [Many-To-Many Relationship in JPA](https://www.baeldung.com/jpa-many-to-many)
* Error Handling
    * [Guide to Spring Boot REST API Error Handling](https://www.toptal.com/java/spring-boot-rest-api-error-handling)
* Validation
    * [Java Bean Validation Basics](https://www.baeldung.com/javax-validation)
    * [Method Constraints with Bean Validation 2.0](https://www.baeldung.com/javax-validation-method-constraints)
* Testing
    * [Testing in Spring Boot](https://www.baeldung.com/spring-boot-testing)
    * Unit Testing
        * [Testing MVC Web Controllers with Spring Boot](https://reflectoring.io/spring-boot-web-controller-test/)
    * Integration Testing
        * [Integration Testing in Spring](https://www.baeldung.com/integration-testing-in-spring)
        * [A Guide to REST-assured](https://www.baeldung.com/rest-assured-tutorial)
            * [Getting and Verifying Response Data with REST-assured](https://www.baeldung.com/rest-assured-response)
    * Assertions
        * [AssertK - Assertions for Kotlin](https://github.com/willowtreeapps/assertk)
* Auditing
    * [Auditing with JPA, Hibernate, and Spring Data JPA](https://www.baeldung.com/database-auditing-jpa)
    * [Spring Data JPA: Auditing](https://springbootdev.com/2018/03/13/spring-data-jpa-auditing-with-createdby-createddate-lastmodifiedby-and-lastmodifieddate/)

#### Kotlin Resources

* [Awesome Kotlin](https://github.com/KotlinBy/awesome-kotlin)
* [Spring](https://spring.io/)
    * [Building web applications with Spring Boot and Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)
    * [Creating a RESTful Web Service with Spring Boot](https://kotlinlang.org/docs/tutorials/spring-boot-restful.html)
    * [Working with Kotlin and JPA](https://www.baeldung.com/kotlin-jpa)
    * [Spring Boot, MongoDB REST API Using Kotlin](https://www.techwasti.com/spring-boot-mongodb-rest-api-using-kotlin/)
* Collections
    * [When to Use Sequences](https://typealias.com/guides/when-to-use-sequences/)
    * [Kotlin Sequences: An Illustrated Guide](https://typealias.com/guides/kotlin-sequences-illustrated-guide/)
* Mapping
    * [Avoiding Boilerplate Code With MapStruct, Spring Boot and Kotlin](https://medium.com/swlh/avoiding-boilerplate-code-with-mapstruct-spring-boot-and-kotlin-50bf5848dc7c)
* Testing
    * [Faker](https://github.com/serpro69/kotlin-faker) - Generates realistically-looking fake data
    * [MockK](https://mockk.io/) - Mocking library for Kotlin
    * Unit Testing
        * [Best Practices for Unit Testing in Kotlin](https://phauer.com/2018/best-practices-unit-testing-kotlin/)
* Video Tutorials/Presentations
    * [FreeCodeCamp: Kotlin Course - Tutorial for Beginners](https://www.youtube.com/watch?v=F9UC9DY-vIU&t=3294s)
    * [GOTO 2018: Kotlin 102 - Beyond the Basics](https://www.youtube.com/watch?v=1KldcFc7HCY)
    * [GOTO 2018: Functional Programming with Kotlin](https://www.youtube.com/watch?v=eNe5Nokrjdg)
    * [GOTO 2018: Lessons from the Backend](https://www.youtube.com/watch?v=xG7na0kTyXk)

#### Additional Resources
    
* Gradle
    * [The New Way of Writing Build Gradle with Kotlin DSL](https://proandroiddev.com/the-new-way-of-writing-build-gradle-with-kotlin-dsl-script-8523710c9670)
* Docker
    * Video Tutorials/Presentations
        * [Docker Tutorial for Beginners](https://www.youtube.com/watch?v=fqMOX6JJhGo)
* Kubernetes
    * Video Tutorials/Presentations
        * [Kubernetes Explained in 100 seconds](https://www.youtube.com/watch?v=PziYflu8cB8)
* [Liquibase Migrations](https://www.liquibase.org/)
    * [Liquibase Works with Plain Old SQL](https://www.liquibase.org/blog/plain-sql)
        * [MySQL 8 - SQL Statements Syntax](https://dev.mysql.com/doc/refman/8.0/en/sql-statements.html)
* Mapping
    * [Performance of Java Mapping Frameworks](https://www.baeldung.com/java-performance-mapping-frameworks)
    * [MapStruct](https://mapstruct.org/)
        * [Mapping immutable POJOs with MapStruct](https://medium.com/trabe/mapping-immutable-pojos-with-mapstruct-3f0bf4627fbc)
* Mocks
    * [MockK](https://mockk.io/)
    * [Mockito](https://site.mockito.org/)
        * [Baeldung Mockito Series](https://www.baeldung.com/tag/mockito/)
    * [WireMock](http://wiremock.org/)
        * [Introduction to WireMock](https://www.baeldung.com/introduction-to-wiremock)
* [Why Immutables Are the Better Objects and How to Implement Them](https://reflectoring.io/java-immutables/)
* Video Tutorials/Presentations
    * [GOTO 2019: Does Agile Make Us Less Secure?](https://www.youtube.com/watch?v=6U41SSz15xw)