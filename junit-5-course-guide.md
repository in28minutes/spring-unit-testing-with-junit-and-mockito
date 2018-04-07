# Unit Testing with JUnit 5

## Learn Unit Testing with most popular framework - JUnit 5

JUnit is most popular Java Unit Testing Framework. The new version of JUnit - Junit 5 or Jupiter is even more special.

In this course, we look into the important features of JUnit 5. We understand the need for unit testing and learn how to write great unit tests with JUnit 5. 

JUnit is a unit testing framework for the Java programming language.

In this Beginners Tutorial on JUnit 5, you will learn how to
- Create a new project for JUnit Tests
- Create and Run JUnit Tests
- Write Good Unit Tests
- Use assert methods
- Use basic JUnit Annotations - @Test, @BeforeEach, @AfterEach, @AfterAll, @BeforeAll
- Test Performance and Exceptions in Unit Tests
- Write Parameterized Tests
- Adhere to JUnit Best Practices
- Use Eclipse to write and run JUnit Tests

### References
- Intellij
  - https://www.jetbrains.com/help/idea/configuring-testing-libraries.html
  - https://blog.jetbrains.com/idea/2016/08/using-junit-5-in-intellij-idea/
- Functional Programming - https://youtu.be/aFCNPHfvqEU
- JUnit - https://junit.org/junit5/docs/current/user-guide/
- Good Unit Testing 
  - FIRST. https://pragprog.com/magazines/2012-01/unit-tests-are-first
  - Patterns - http://xunitpatterns.com
- More Advanced Stuff
	- AssertJ - https://joel-costigliola.github.io/assertj/
	- Mockito - https://github.com/mockito/mockito/wiki/FAQ
	- JsonPath - https://github.com/json-path/JsonPath
	- Setting up JUnit 5 with Mockito and Spring Boot 2.0 - https://medium.com/@dSebastien/using-junit-5-with-spring-boot-2-kotlin-and-mockito-d5aea5b0c668

### Recommended Tools
- Java 8 or Later
- Eclipse Oxygen or Later
- Spring Boot 2.0.0.RELEASE or Later

### Installing Java 8 and Eclipse
- Installation Video : https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3
- GIT Repository For Installation : https://github.com/in28minutes/getting-started-in-5-steps
- PDF : https://github.com/in28minutes/SpringIn28Minutes/blob/master/InstallationGuide-JavaEclipseAndMaven_v2.pdf

### What You will learn
- You will learn to write unit tests with JUnit 5
- You will learn to run unit tests with Eclipse
- You will learn about variety of JUnit assert methods
- You will understand basic JUnit Annotations - @Test, @BeforeEach, @AfterEach, @AfterAll, @BeforeAll
- You will learn to test performance and exceptions in unit tests
- You will learn to write parameterized tests
- You will learn the differences between JUnit 4 and JUnit 5
- You will learn about the JUnit Best Practices

### Requirements
- You should have working knowledge of Java and Annotations. 
- We will help you install Eclipse and get up and running with Maven and Tomcat.

### Exercises
- Step 04 - First Unit Test with JUnit - String length() method
	- Write unit test for Math.min and Math.max methods!
- Step 05 - Writing JUnit Assertions - assertNull and assertTrue
	- Exercise : Write assertEquals for two other data types
- Step 06 - Writing Assertions for Arrays - assertArrayEquals
	- Spot the bug : Reverse expected and actual values
- Step 11 - Basics of Parameterized tests 
	- Write unit tests using ints attribute of Parameterized Test for a Math method.

### Step By Step Details
- Step 01 - Introduction to Unit Testing - Test Pyramid
- Step 02 - First Junit Test - Red bar
- Step 03 - Absence of failure is success
- Step 04 - First Unit Test with JUnit - String length() method
- Step 05 - Writing JUnit Assertions - assertNull and assertTrue
- Step 06 - Writing Assertions for Arrays - assertArrayEquals
- Step 07 - Setting up data for every test - @BeforeEach, @AfterEach
- Step 08 - Setting up database connections - @BeforeAll, @AfterAll
- Step 09 - Tip - Testing Exceptions with JUnit
- Step 10 - Tip - @DisplayName and test methods need not be public
- Step 11 - Basics of Parameterized tests 
- Step 12 - Advanced Paramaterized Tests with Csv Source
- Step 13 - Tip - Giving a name to a Parameterized Test
- Step 14 - Tip - Repeat same test multiple times
- Step 15 - Tip - Unit Testing for Performance
- Step 16 - Tip - Disable Unit Tests
- Step 17 - Tip - Grouping Tests with @Nested
- Step 18 - Tip -  JUnit 5 = Platform + Jupiter + Vintage
- Step 19 - Tip -  JUnit 4 vs JUnit 5
- Step 20 - Tip - JUnit Best Practices
- Step 21 - Tip - JUnit Patterns - http://xunitpatterns.com

### Course Notes

#### Test Pyramid
![Test Pyramid](images/TestPyramid.png)

#### JUnit 5
JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
- JUnit Platform = Building Blocks
- JUnit Jupiter = new programming model +  extension model + extensions - assertions, annotations
- JUnit Vintage = Support for old JUnit version ( 3 & 4)

#### JUnit 5 vs JUnit 4
- Neither test classes nor test methods need to be public

Annotations
- Same as in JUnit 4 - @Test
- Renamed
  - @BeforeAll instead of @BeforeClass
    - @AfterAll instead of @AfterClass
    - @BeforeEach instead of @Before
    - @AfterEach instead of @After
  - @Disable instead of @Ignore
  - @Tag  instead of @Category
- New 
  - @DisplayName
  - @TestFactory for dynamic tests
  - @Nested for nested tests
  - @RepeatedTest to executed tests multiple times
  - @EnabledOnOs
  - @DisabledOnOs
  - @EnabledOnJre

Assertions
- Same as JUnit 4
  - assertTrue, assertSame, assertNull, assertNotSame, assertNotEquals, assertNotNull, assertFalse, assertEquals, assertArrayEquals
- New
  - assertThrows and assertTimeout

### Course Recording Notes

#### Preview Video
- Welcome to course on *** in ** simple steps.
- I'm Ranga Karanam. I've so and so much experience with ... I've been using this framework for ...
- At in28minutes, we ask one question everyday - How to create more effective courses? All our success - *** students on Udemy and *** subscribers on Youtube - is a result of this pursuit of excellence.
- You will develop *** and *** using ***
- You will learn the basics like *** and move on to the advanced concepts like ***.
- You will use 
  - ... todo ...
  - Maven for dependency management, building and running the application in tomcat.
  - Eclipse IDE
- All the code for this course and the step by step details are in our Github repository. 
- We have an awesome installation guide to help you install Maven and Eclipse. You are NOT expected to have any experience with Eclipse, Maven, or Tomcat.
- What are we waiting for? Lets have some fun with *** in *** steps. We had a lot of fun creating this course for you and We are confident that you will have a lot of fun. I hope you are as excited as we are to learn more. Go ahead and enroll for the course. Or take a test drive with a free preview. See you in the course.

#### Conclusion Video
- Congratulations! You have successfully completed the course on ... We covered a wide range of topics starting from Spring, Spring Boot to ..... I'm sure you had a lot of fun doing this course. If you loved this course, we would love to hear from you. Do not forget to leave us a review. Until we see you in another in28minutes course, here's bye from the team here at in28minutes.
- To find out more about *** use these References  

## Templates

### Welcome Message
```

## ADD A FEW SAMPLE REVIEWS AFter a couple of months
## ADD A FEW SAMPLE REVIEWS - in the description of the course 

Congratulations on joining this course from in28Minutes. 

We have 6800+ 5 Star reviews on our courses.  

I hope that by the time you are prompted to leave a review, that you think this course is an amazing course and can write a few sentences about what you like about the course for future students to see. If you do not think this course is a 5-star course, we want to make it a better course for you! Please message me with ways that I can make it the best course for you.

There are three things you need to understand before you start this course!

1...... Listen + See + Do Hands-on + Repeat = 90% Retention
For the first 2 hours, we repeat a few concepts to help you retain them. .

2...... Set Yourself a Goal
Set 1 hour aside every day for the next week for this course! No exceptions allowed :) 

3...... Udemy asks you for a review very early in the course! If you are not ready for giving a review, you can skip giving a review.

Thank you and enjoy the course,
Ranga
```

### Thank You for completing the course message

```
Congratulations on completing the course from in28Minutes.

We have 6800+ 5 Star reviews on our courses. We hope you think this course is an amazing course and can write a few sentences about what you like about the course for future students to see.

We are on a constant journey to improve our courses further. Do message me if you have any suggestions.

Good Luck for your future.

Ranga from in28Minutes
```

### Bonus Lectures

```
Our Best Selling Courses
Get unbelievable offers on all our best-selling courses!
http://eepurl.com/bOJulL
```

### JUnit 5 Complete Code Example

```java
package com.in28minutes.junit5;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	
	private String str;

	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("Close connection to database");
	}

	@BeforeEach // @Before
	void beforeEach(TestInfo info) {
		System.out.println("Initialize Test Data for  " + info.getDisplayName());
	}

	@AfterEach // @After
	void afterEach(TestInfo info) {
		System.out.println("Clean up Test Data for  " + info.getDisplayName());
	}

	@Test
	@Disabled // @Ignored
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);
		// Assert length == 4
		// Write test code
		// Invoke method square(4) => CUT
		// Checks in place - 16 => Assertions
	}

	@Test
	void length_greater_than_zero() {
		assertTrue("ABCD".length() > 0);
		assertTrue("ABC".length() > 0);
		assertTrue("A".length() > 0);
		assertTrue("DEF".length() > 0);
	}

	@ParameterizedTest
	@ValueSource(strings = { "ABCD", "ABC", "A", "DEF" })
	void length_greater_than_zero_using_parameterized_test(String str) {
		assertTrue(str.length() > 0);
	}

	@ParameterizedTest(name = "{0} toUpperCase is {1}")
	@CsvSource(value = { "abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG" })
	void uppercase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}

	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = { "abcd, 4", "abc, 3", "'',0", "abcdefg, 7" })
	void length(String word, int expectedLength) {
		assertEquals(expectedLength, word.length());
	}

	@Test
	@DisplayName("When length is null, throw an exception")
	void length_exception() {

		String str = null;

		assertThrows(NullPointerException.class,

				() -> {
					str.length();
				}

		);
	}

	@Test
	@Disabled
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5), () -> {
			for (int i = 0; i <= 1000000; i++) {
				int j = i;
				System.out.println(j);
			}
		}

		);
	}

	@Test
	void toUpperCase_basic() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertNotNull(result);
		// assertNull(result);
		assertEquals("ABCD", result);
	}

	@Test
	@RepeatedTest(1)
	void contains_basic() {
		assertFalse("abcdefgh".contains("ijk"));
	}

	@Test
	void split_basic() {
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		String[] expectedResult = new String[] { "abc", "def", "ghi" };
		assertArrayEquals(expectedResult, actualResult);
	}
	
	@Nested
	@DisplayName("For an empty String")
	class EmptyStringTests {
		
		@BeforeEach
		void setToEmpty() {
			str = "";
		}
		
		@Test
		@DisplayName("length should be zero")
		void lengthIsZero() {
			assertEquals(0,str.length());
		}
		
		@Test
		@DisplayName("upper case is empty")
		void uppercaseIsEmpty() {
			assertEquals("",str.toUpperCase());
		}

	}

	@Nested
	@DisplayName("For large strings")
	class LargeStringTests {
		
		@BeforeEach
		void setToALargeString() {
			str = "Afsjdjfljsadfkljsadlkfjlajbvjcxzbnhrewu";
		}
		
		@Test
		void test() {
			
		}

	}
}
```

### Useful Links
- [Our Website](http://www.in28minutes.com)
- [Facebook](http://facebook.com/in28minutes)
- [Twitter](http://twitter.com/in28minutes)
- [Google Plus](https://plus.google.com/u/3/110861829188024231119)