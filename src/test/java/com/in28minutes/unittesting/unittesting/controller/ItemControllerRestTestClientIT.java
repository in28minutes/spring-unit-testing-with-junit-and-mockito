package com.in28minutes.unittesting.unittesting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

//Step 32 - the same integration test as ItemControllerIT, using RestTestClient's own
//assertions. No manual byte[] to String, no separate JSONAssert call.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class ItemControllerRestTestClientIT {

	@Autowired
	private RestTestClient restTestClient;

	@Test
	void retrieveAllItems_jsonComparison() {
		restTestClient.get().uri("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody().json("[{id:10001},{id:10002},{id:10003}]");
	}

	//jsonPath drills into single values, so the whole payload does not have to be spelled out
	@Test
	void retrieveAllItems_individualValues() {
		restTestClient.get().uri("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.length()").isEqualTo(3)
				.jsonPath("$[0].name").isEqualTo("Item1")
				.jsonPath("$[0].value").isEqualTo(200);
	}

	@Test
	void unknownUri_isNotFound() {
		restTestClient.get().uri("/no-such-endpoint")
				.exchange()
				.expectStatus().isNotFound();
	}
}
