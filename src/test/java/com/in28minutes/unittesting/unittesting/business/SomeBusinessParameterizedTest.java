package com.in28minutes.unittesting.unittesting.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//Step 28 - calculateSum_basic, _empty and _oneValue are the same test with different data.
//One @ParameterizedTest replaces all three.
@DisplayName("calculateSum - parameterized")
class SomeBusinessParameterizedTest {

	private final SomeBusinessImpl business = new SomeBusinessImpl();

	//@ValueSource - one argument per run
	@ParameterizedTest(name = "a single value of {0} sums to itself")
	@ValueSource(ints = { 1, 5, 100 })
	void singleValue(int value) {
		assertEquals(value, business.calculateSum(new int[] { value }));
	}

	//@CsvSource - several arguments per run, read left to right
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({ "1, 2, 3", "10, 20, 30", "0, 0, 0", "-5, 5, 0" })
	void twoValues(int first, int second, int expectedSum) {
		assertEquals(expectedSum, business.calculateSum(new int[] { first, second }));
	}

	//@MethodSource - the only one of the three that can supply an array
	@ParameterizedTest(name = "sums to {1}")
	@MethodSource("dataAndExpectedSum")
	void anyNumberOfValues(int[] data, int expectedSum) {
		assertEquals(expectedSum, business.calculateSum(data));
	}

	static Stream<Arguments> dataAndExpectedSum() {
		return Stream.of(
				arguments(new int[] { 1, 2, 3 }, 6),
				arguments(new int[] {}, 0),
				arguments(new int[] { 5 }, 5));
	}
}
