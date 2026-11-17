package com.in28minutes.unittesting.unittesting.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import com.in28minutes.unittesting.unittesting.data.ItemRepository;
import com.in28minutes.unittesting.unittesting.model.Item;

//Step 31 - @MockitoBean replaces a bean with a mock. @MockitoSpyBean wraps the real one,
//so the real repository still hits H2 and the call can still be verified.
@SpringBootTest
class ItemBusinessServiceSpyBeanTest {

	@MockitoSpyBean
	private ItemRepository repository;

	@Autowired
	private ItemBusinessService businessService;

	@Test
	void retrieveAllItems_realRepositoryRunsAndIsVerified() {
		List<Item> items = businessService.retrieveAllItems();

		//The three rows from data.sql came back, so nothing was stubbed away.
		//findAll() has no ORDER BY, so assert the values as a set, not by position.
		assertThat(items).hasSize(3)
				.extracting(Item::getValue)
				.containsExactlyInAnyOrder(200, 50, 30);

		verify(repository).findAll();
	}
}
