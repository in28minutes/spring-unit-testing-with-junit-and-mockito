package com.in28minutes.unittesting.unittesting.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

//Step 30 - the JSON slice. No web layer, no database, no controller - just the mapper.
//Enough to answer whether Item serializes the way the API promises.
@JsonTest
class ItemJsonTest {

	@Autowired
	private JacksonTester<Item> json;

	@Test
	void serializesEveryField() throws IOException {
		Item item = new Item(1, "Ball", 10, 100);

		assertThat(json.write(item))
				.isStrictlyEqualToJson("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100,\"value\":0}");
	}

	//@Transient keeps value out of the database, but Jackson still sees the getter and writes it
	@Test
	void serializesTheTransientValueField() throws IOException {
		Item item = new Item(1, "Ball", 10, 100);
		item.setValue(1000);

		assertThat(json.write(item))
				.hasJsonPathNumberValue("$.value")
				.extractingJsonPathNumberValue("$.value").isEqualTo(1000);
	}
}
