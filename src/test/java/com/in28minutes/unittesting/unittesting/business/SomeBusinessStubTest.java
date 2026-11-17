package com.in28minutes.unittesting.unittesting.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

//Every stub below had to implement storeSum when it was added to the interface.
//A mock needs no such change - that is the maintenance cost this step is about.
//See "Changes to existing code" in 99-hafeez-module-additions.md for why storeSum was added.
class SomeDataServiceStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 1, 2, 3 };
	}

	@Override
	public void storeSum(int sum) {
	}
}

class SomeDataServiceEmptyStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { };
	}

	@Override
	public void storeSum(int sum) {
	}
}

class SomeDataServiceOneElementStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 5 };
	}

	@Override
	public void storeSum(int sum) {
	}
}

public class SomeBusinessStubTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStub());
		int actualResult = business.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceEmptyStub());
		int actualResult = business.calculateSumUsingDataService();//new int[] {}
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceOneElementStub());
		int actualResult = business.calculateSumUsingDataService();//new int[] { 5 }
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}
}
