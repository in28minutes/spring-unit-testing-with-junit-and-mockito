package com.in28minutes.unittesting.unittesting.business;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.convention.TestBean;

import com.in28minutes.unittesting.unittesting.controller.ItemController;
import com.in28minutes.unittesting.unittesting.model.Item;

//Step 31 - @TestBean is the third bean override: no Mockito at all, just your own object.
//Reach for it when a hand-written fake is clearer than stubbing every call.
@SpringBootTest
class ItemBusinessServiceTestBeanTest {

	@TestBean
	private ItemBusinessService businessService;

	@Autowired
	private ItemController controller;

	//Resolved by name: the field is businessService, so the factory method must be too.
	//It has to be static and take no arguments.
	static ItemBusinessService businessService() {
		return new ItemBusinessService() {
			@Override
			public Item retreiveHardcodedItem() {
				return new Item(99, "Fake Item", 1, 1);
			}
		};
	}

	@Test
	void controllerReceivesTheReplacementBean() {
		assertThat(controller.itemFromBusinessService().getName()).isEqualTo("Fake Item");
	}
}
