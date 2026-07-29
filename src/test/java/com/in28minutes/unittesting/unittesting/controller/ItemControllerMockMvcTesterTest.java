package com.in28minutes.unittesting.unittesting.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.in28minutes.unittesting.unittesting.business.ItemBusinessService;
import com.in28minutes.unittesting.unittesting.model.Item;

//Step 29 - the same endpoints as ItemControllerTest, written with MockMvcTester.
//@WebMvcTest auto-configures it alongside MockMvc whenever AssertJ is on the classpath.
@WebMvcTest({ HelloWorldController.class, ItemController.class })
class ItemControllerMockMvcTesterTest {

	@Autowired
	private MockMvcTester mvc;

	@MockitoBean
	private ItemBusinessService businessService;

	//No RequestBuilder, no andExpect, no throws Exception.
	@Test
	void helloWorld_basic() {
		assertThat(mvc.get().uri("/hello-world"))
				.hasStatusOk()
				.hasBodyTextEqualTo("Hello World");
	}

	@Test
	void dummyItem_basic() {
		assertThat(mvc.get().uri("/dummy-item").accept(MediaType.APPLICATION_JSON))
				.hasStatusOk()
				.bodyJson()
				.isLenientlyEqualTo("{id:1,name:Ball,price:10,quantity:100}");
	}

	@Test
	void itemFromBusinessService_basic() {
		when(businessService.retreiveHardcodedItem()).thenReturn(new Item(2, "Item2", 10, 10));

		assertThat(mvc.get().uri("/item-from-business-service").accept(MediaType.APPLICATION_JSON))
				.hasStatusOk()
				.bodyJson()
				.isLenientlyEqualTo("{id:2,name:Item2,price:10}");
	}

	//hasPathSatisfying reaches into the response with JSONPath, so no full JSON string is needed
	@Test
	void retrieveAllItems_basic() {
		when(businessService.retrieveAllItems())
				.thenReturn(List.of(new Item(2, "Item2", 10, 10), new Item(3, "Item3", 20, 20)));

		assertThat(mvc.get().uri("/all-items-from-database").accept(MediaType.APPLICATION_JSON))
				.hasStatusOk()
				.bodyJson()
				.hasPathSatisfying("$.length()", length -> assertThat(length).isEqualTo(2))
				.hasPathSatisfying("$[0].name", name -> assertThat(name).isEqualTo("Item2"));
	}

	@Test
	void retrieveAllItems_noitems() {
		when(businessService.retrieveAllItems()).thenReturn(List.of());

		assertThat(mvc.get().uri("/all-items-from-database").accept(MediaType.APPLICATION_JSON))
				.hasStatusOk()
				.bodyJson()
				.isLenientlyEqualTo("[]");
	}
}
