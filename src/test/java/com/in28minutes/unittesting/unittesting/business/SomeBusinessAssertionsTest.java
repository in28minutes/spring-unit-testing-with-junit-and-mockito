package com.in28minutes.unittesting.unittesting.business;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

//Step 27 - assertAll, assertThrows, @Nested and @Timeout, alongside the assertEquals used so far.
@ExtendWith(MockitoExtension.class)
@DisplayName("SomeBusinessImpl")
class SomeBusinessAssertionsTest {

	@InjectMocks
	private SomeBusinessImpl business;

	@Mock
	private SomeDataService dataServiceMock;

	@Nested
	@DisplayName("calculateSum")
	class CalculateSum {

		@Test
		@DisplayName("adds up every value in the array")
		void addsUpEveryValue() {
			assertEquals(6, business.calculateSum(new int[] { 1, 2, 3 }));
		}

		//Three separate assertEquals calls would stop at the first failure.
		//assertAll evaluates all three and reports every failure together.
		@Test
		@DisplayName("reports every failure in a single run")
		void reportsEveryFailureAtOnce() {
			assertAll("calculateSum",
					() -> assertEquals(6, business.calculateSum(new int[] { 1, 2, 3 })),
					() -> assertEquals(0, business.calculateSum(new int[] {})),
					() -> assertEquals(5, business.calculateSum(new int[] { 5 })));
		}

		//The exception is the expected behaviour here, so it is asserted, not caught.
		@Test
		@DisplayName("throws when the array is null")
		void throwsWhenArrayIsNull() {
			assertThrows(NullPointerException.class, () -> business.calculateSum(null));
		}
	}

	@Nested
	@DisplayName("storeSumUsingDataService")
	class StoreSumUsingDataService {

		//Returns void, so there is nothing to assert on. Verification is the only option.
		@Test
		@DisplayName("stores the sum it just calculated")
		void storesTheCalculatedSum() {
			when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 1, 2, 3 });

			business.storeSumUsingDataService();

			verify(dataServiceMock).storeSum(6);
		}

		//Fails the test instead of hanging the build if the call ever starts blocking.
		@Test
		@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
		@DisplayName("finishes well inside half a second")
		void finishesQuickly() {
			when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 1, 2, 3 });

			business.storeSumUsingDataService();

			verify(dataServiceMock).storeSum(6);
		}
	}
}
