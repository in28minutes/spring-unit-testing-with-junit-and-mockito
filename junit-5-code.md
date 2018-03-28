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