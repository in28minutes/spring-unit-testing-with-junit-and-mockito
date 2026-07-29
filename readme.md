# Unit Testing with Spring, JUnit and Mockito

[![Image](https://www.springboottutorial.com/images/Course-Master-Java-Unit-Testing-with-Spring-Boot-Mockito.png "Master Java Unit Testing with Spring Boot & Mockito")](https://www.udemy.com/course/learn-unit-testing-with-spring-boot/)

## Learn Unit Testing with most popular frameworks - Spring Boot, JUnit and Mockito

Spring Boot is the most popular framework to develop RESTful Services. It has awesome unit testing capabilities through Spring Boot Starter Test. Mockito is the most popular mocking framework. JUnit is most popular Java Unit Testing Framework.

In this course, you will learn to build unit tests for simple RESTful Services with Spring Boot Starter Test, Mockito and JUnit. You will learn to write independent unit tests for RESTful web services talking with multiple layers - web, business and data. You will learn how to write integration tests using an in memory database H2.

You will build the unit tests step by step - in more than 50 steps. This course would be a perfect first step as an introduction to unit testing with Spring Boot and Mockito Frameworks.

You will be using Spring (Dependency Management), Spring Boot, Maven (dependencies management), Eclipse (IDE), in memory database H2 and Tomcat Embedded Web Server. We will help you set up each one of these.

You will use all the frameworks that are part of Spring Boot Starter Test - JUnit, Spring Test, Spring Boot Test, AssertJ, Hamcrest, Mockito, JSONassert and JsonPath.

You will learn to use the most important Unit Testing Annotations - @ExtendWith(MockitoExtension.class), @SpringBootTest, @WebMvcTest, @DataJpaTest, @MockBean, @MockitoBean, 

### Recommended Tools
- Java 17 or Later
- Eclipse Oxygen or Later
- Spring Boot 3.0.x or 4.0.x 

### Installing Java 8 and Eclipse
- Installation Video : https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3
- GIT Repository For Installation : https://github.com/in28minutes/getting-started-in-5-steps
- PDF : https://github.com/in28minutes/SpringIn28Minutes/blob/master/InstallationGuide-JavaEclipseAndMaven_v2.pdf

### References
- Intellij
  - https://www.jetbrains.com/help/idea/configuring-testing-libraries.html
  - https://blog.jetbrains.com/idea/2016/08/using-junit-5-in-intellij-idea/
- Spring & Spring Boot Framework - https://www.youtube.com/watch?v=PSP1-2cN7vM&t=893s
- Introduction to JPA and Hibernate using Spring Boot Data Jpa - http://www.springboottutorial.com/introduction-to-jpa-with-spring-boot-data-jpa
- Functional Programming - https://youtu.be/aFCNPHfvqEU
- JUnit - https://junit.org/junit5/docs/current/user-guide/
- AssertJ - https://joel-costigliola.github.io/assertj/
- Mockito - https://github.com/mockito/mockito/wiki/FAQ
- JsonPath - https://github.com/json-path/JsonPath
- Setting up JUnit 5 with Mockito and Spring Boot 2.0 - https://medium.com/@dSebastien/using-junit-5-with-spring-boot-2-kotlin-and-mockito-d5aea5b0c668
- Good Unit Testing 
  - https://github.com/mockito/mockito/wiki/How-to-write-good-tests
  - FIRST. https://pragprog.com/magazines/2012-01/unit-tests-are-first
  - Patterns - http://xunitpatterns.com
- Mocking Static, Private Methods and Constructors 
  - https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step15.md
  - https://github.com/in28minutes/MockitoTutorialForBeginners/tree/master/src/test/java/com/in28minutes/powermock

### Mockito

Easier Static Imports
```
Window > Preferences > Java > Editor > Content Assist > Favorites
org.junit.Assert
org.mockito.BDDMockito
org.mockito.Mockito
org.assertj.core.api.Assertions
org.hamcrest.Matchers
org.hamcrest.CoreMatchers
org.hamcrest.MatcherAssert
```
### What You will learn
- You will learn to write great Unit and Integration tests using Spring Boot Starter Test
- You will learn to write unit tests using Mocks and Spys created with Mockito
- You will learn to write independent unit tests for RESTful web services talking with multiple layers - web, business and data
- You will learn to write integration tests using an in memory database - H2
- You will learn to use all the frameworks that are part of Spring Boot Starter Test - JUnit, Spring Test, Spring Boot Test, AssertJ, Hamcrest, Mockito, JSONassert and JsonPath.
- You will learn to use the most important Unit Testing Annotations - @ExtendWith(MockitoExtension.class), @SpringBootTest, @WebMvcTest, @DataJpaTest and @MockBean.

### Requirements
- You should have working knowledge of Java and Annotations. 
- We will help you install Eclipse and get up and running with Maven and Tomcat.
- You should have basic knowledge about Spring, Spring Boot and JPA/Hibernate. We provide resources that can be used as starting points to enrich your knowledge in the course.
                                                                                
## Mockito

### Step By Step Details

- Step 01: Setting up the project using Spring Initializr
- Step 02: Writing Unit Test for a Simple Business Service
- Step 03: Setting up a Business Service to call a Data Service
- Step 04: Writing your first unit test with Stub
  - Exercise - Update Tests 2 & 3
- Step 05: Exercise Solution - Updating Tests 2 & 3 to use Stubs - Problem with Stubs.
- Step 06: Writing Unit Tests with Mocking using Mockito
  - Exercise - Updating Tests 2 & 3 to use Mockito
- Step 07: Exercise Solution - Updating Tests 2 & 3 to use Mockito
- Step 08: More Refactoring - @Mock, @InjectMocks and @RunWith(MockitoJUnitRunner.class)
- Step 09: Mockito Tips - Multiple Return Values and Specific Argument Matchers
- Step 10: Mockito Tips - Argument Matchers
- Step 11: Mockito Tips - Verify method calls
- Step 12: Mockito Tips - Argument Capture
- Step 13: Mockito Tips - Argument Capture on Multiple Calls
- Step 14: Introduction to Spy
- Step 15: Mockito FAQ

## Spring Boot & Mockito - Unit Testing

### Step By Step Details

- Step 01: Creating a Hello World Controller
- Step 02: Using Mock Mvc to test Hello World Controller
- Step 03: Using Response Matchers to check status and content
- Step 04: Creating a Basic REST Service in Item Controller
- Step 05: Unit Testing Item Controller and Basic JSON Assertions
- Step 06: Digging deeper into JSON Assert
- Step 07: Writing a REST Service talking to Business Layer
- Step 08: Writing Unit Test for REST Service mocking Business Layer
- Step 09 - 01 - Prepare Data Layers with JPA, Hibernate and H2
- Step 10: Create Item Entity and Populate data with data.sql
- Step 11: Create a RESTful Service talking to the database
- Step 12: Writing Unit Test for Web Layer - Controller - Using Mock MVC
- Step 13: Exercise & Solution - Writing Unit Test for Business Layer - Mocking
- Step 14: Writing Unit Test for Data Layer - Data JPA Test
  - Exercise - Write Unit Test for findById method retrieving item with id 10001
- Step 15: Writing an Integration Test using @SpringBootTest
  - Exercise - Make Asserts Better
- Step 16: Tip : Using @MockBean to mock out dependencies you do not want to talk to!
- Step 17: Tip : Creating Different Test Configuration
- Step 18: Writing Unit Tests for Other Request Methods
- Step 19: Refactor SomeBusinessImpl to use Functional Programming
  - Exercise - Convert the second method to use Functional Approach
- Step 20: Better Assertions with Hamcrest - HamcrestMatcherTest
- Step 21: Better Assertions with AssertJ - AssertJTest
- Step 22: Better Assertions with JSONPath - JSONPathTest
- Step 23: Easier Static Imports
- Step 24: Tip : Measuring Test Coverage with Eclipse
- Step 25: Tip : Keep an eye on performance of unit tests!
- Step 26: Good Unit Tests

### Additions - Spring Boot 4, Spring Framework 7 and JUnit 6

Details, code and references - [99-hafeez-module-additions.md](99-hafeez-module-additions.md)

- Step 27: JUnit 6 Assertions - @DisplayName, assertThrows, assertAll, @Nested, @Timeout
- Step 28: One Parameterized Test Replaces Three - @ValueSource, @CsvSource, @MethodSource
- Step 29: MockMvcTester - MockMvc with AssertJ
- Step 30: @JsonTest and JacksonTester - Testing Serialization on Its Own
- Step 31: The Other Two Bean Overrides - @MockitoSpyBean and @TestBean
- Step 32: RestTestClient, Using Its Own Assertions
- Step 33: Coverage with JaCoCo, and the Tests That Were Not Running

```
mvn test      # unit tests
mvn verify    # unit tests + integration tests + target/site/jacoco/index.html
```

### Troubleshooting
- Refer our TroubleShooting Guide - https://github.com/in28minutes/in28minutes-initiatives/tree/master/The-in28Minutes-TroubleshootingGuide-And-FAQ

## Youtube Playlists - 500+ Videos

[Click here - 30+ Playlists with 500+ Videos on Spring, Spring Boot, REST, Microservices and the Cloud](https://www.youtube.com/user/rithustutorials/playlists?view=1&sort=lad&flow=list)

## Keep Learning in28Minutes

in28Minutes is creating amazing solutions for you to learn Spring Boot, Full Stack and the Cloud - Docker, Kubernetes, AWS, React, Angular etc. - [Check out all our courses here](https://github.com/in28minutes/learn)

![in28MinutesLearningRoadmap-July2019.png](https://github.com/in28minutes/in28Minutes-Course-Roadmap/raw/master/in28MinutesLearningRoadmap-July2019.png)

## References

Versions this project builds against - Spring Boot 4.0.0, spring-test 7.0.1, JUnit Jupiter 6.0.1, Mockito 5.20.0, AssertJ 3.27.6, Hamcrest 3.0, JSONassert 1.5.3, JsonPath 2.9.0.

### Spring Boot 4 and Spring Framework 7
- Testing Spring Boot Applications - https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html
- Spring Boot 4.0 Migration Guide - https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide
- What's new for testing in Spring Boot 4 and Spring Framework 7 - https://rieckpil.de/whats-new-for-testing-in-spring-boot-4-0-and-spring-framework-7/
- The state of HTTP clients in Spring - RestTemplate to RestClient - https://spring.io/blog/2025/09/30/the-state-of-http-clients-in-spring

### MockMvc and REST testing
- MockMvc AssertJ support - MockMvcTester - https://docs.spring.io/spring-framework/reference/testing/mockmvc/assertj.html
- MockMvcTester javadoc - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/assertj/MockMvcTester.html
- RestTestClient javadoc - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/client/RestTestClient.html
- @JsonTest javadoc - https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/autoconfigure/json/JsonTest.html
- JacksonTester javadoc - https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/json/JacksonTester.html

### Bean overriding - @MockitoBean, @MockitoSpyBean, @TestBean
- Bean Overriding in Tests - https://docs.spring.io/spring-framework/reference/testing/testcontext-framework/bean-overriding.html
- @MockitoBean and @MockitoSpyBean - https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-mockitobean.html
- @TestBean - https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-testbean.html

### JUnit 6
- JUnit User Guide - https://docs.junit.org/current/user-guide/
- Test Classes and Methods - visibility rules - https://docs.junit.org/current/writing-tests/test-classes-and-methods.html
- Parameterized Tests - https://docs.junit.org/current/user-guide/#writing-tests-parameterized-tests
- Nested Tests - https://docs.junit.org/current/user-guide/#writing-tests-nested
- Spring TestContext support for JUnit Jupiter - https://docs.spring.io/spring-framework/reference/testing/annotations/integration-junit-jupiter.html

### Mockito
- Mockito javadoc - https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html
- Mockito as a Java agent - JEP 451 - https://openjdk.org/jeps/451

### Build and coverage
- JaCoCo Maven plugin - https://www.jacoco.org/jacoco/trunk/doc/maven.html
- JaCoCo change history and supported Java versions - https://www.jacoco.org/jacoco/trunk/doc/changes.html
- maven-failsafe-plugin - running *IT integration tests - https://maven.apache.org/surefire/maven-failsafe-plugin/
- maven-surefire-plugin default test includes - https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html#includes
- Introducing Jackson 3 support in Spring - https://spring.io/blog/2025/10/07/introducing-jackson-3-support-in-spring/
