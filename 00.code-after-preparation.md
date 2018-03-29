<!---
Current Directory : /in28Minutes/git/getting-started-in-5-steps/spring-unit-testing-with-mockito
-->

## Complete Code Example

### /src/main/java/com/in28minutes/springunittestingwithmockito/business/ItemService.java

```java
package com.in28minutes.springunittestingwithmockito.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springunittestingwithmockito.data.ItemRepository;
import com.in28minutes.springunittestingwithmockito.entity.Item;

@Component
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public List<Item> calculateTotalValue() {

		List<Item> items = repository.findAll();

		items.stream().forEach((item) -> {
			item.setValue(item.getPrice() * item.getQuantity());
		});

		return items;
	}
	
	public void insertItem() {
		
	}

}
```
---

### /src/main/java/com/in28minutes/springunittestingwithmockito/business/SomeBusinessService.java

```java
package com.in28minutes.springunittestingwithmockito.business;

import java.util.Arrays;

import com.in28minutes.springunittestingwithmockito.data.SomeDataService;

public class SomeBusinessService {

	private SomeDataService someData;

	public SomeBusinessService(SomeDataService someData) {
		super();
		this.someData = someData;
	}

	public int calculateSum() {
		return Arrays.stream(someData.retrieveData())
				.reduce(Integer::sum).orElse(0);
	}
}
```
---

### /src/main/java/com/in28minutes/springunittestingwithmockito/controller/ItemController.java

```java
package com.in28minutes.springunittestingwithmockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springunittestingwithmockito.business.ItemService;
import com.in28minutes.springunittestingwithmockito.entity.Item;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@GetMapping("/items")
	public List<Item> retrieveAllItems() {
		return service.calculateTotalValue();
	}

}
```
---

### /src/main/java/com/in28minutes/springunittestingwithmockito/data/ItemRepository.java

```java
package com.in28minutes.springunittestingwithmockito.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springunittestingwithmockito.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
```
---

### /src/main/java/com/in28minutes/springunittestingwithmockito/data/SomeDataService.java

```java
package com.in28minutes.springunittestingwithmockito.data;

public class SomeDataService {
	public int[] retrieveData() {
		throw new RuntimeException("Unimplemented");
	}
}
```
---

### /src/main/java/com/in28minutes/springunittestingwithmockito/entity/Item.java

```java
package com.in28minutes.springunittestingwithmockito.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Item {
	@Id
	private long id;
	private String name;
	private int quantity;
	private int price;

	@Transient
	private long value;

	public Item() {

	}

	public Item(int id, String name, int quantity, int price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
```
---

### /src/main/java/com/in28minutes/springunittestingwithmockito/SpringUnitTestingWithMockitoApplication.java

```java
package com.in28minutes.springunittestingwithmockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringUnitTestingWithMockitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUnitTestingWithMockitoApplication.class, args);
	}
}
```
---

### /src/main/resources/application.properties

```properties
spring.h2.console.enabled=true
spring.jpa.show-sql=true
```
---

### /src/main/resources/data.sql

```
insert into item (id, name, quantity, price) values(10001, 'Chocolates', 25, 2);
insert into item (id, name, quantity, price) values(10002, 'Biscuits', 50, 2);
insert into item (id, name, quantity, price) values(10003, 'Pens', 25, 3);
insert into item (id, name, quantity, price) values(10004, 'Pencils', 25, 2);
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/AssertjTest.java

```java
package com.in28minutes.springunittestingwithmockito;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AssertjTest {

	@Test
	public void basicHamcrestMatchers() {
		//List
		List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		
		assertThat(scores).hasSize(4);
		assertThat(scores).contains(100, 101);
		assertThat(scores).allMatch(x -> x > 90);
		assertThat(scores).allMatch(x -> x < 200);

		// String
		assertThat("").isEmpty();

		// Array
		Integer[] marks = { 1, 2, 3 };

		assertThat(marks).hasSize(3);
		assertThat(marks).contains(2, 3, 1);

	}
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/business/ItemServiceTest.java

```java
package com.in28minutes.springunittestingwithmockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.in28minutes.springunittestingwithmockito.data.ItemRepository;
import com.in28minutes.springunittestingwithmockito.entity.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
	
	@Mock
	ItemRepository repository;
	
	@InjectMocks
	ItemService service;
	
	@Test
	public void testWithMock_usingMockitoRunner() {
		List<Item> mockList = Arrays.asList(new Item(1, "Dummy", 10, 5));
		
		when(repository.findAll()).thenReturn(mockList);
		
		List<Item> items = service.calculateTotalValue();
		assertEquals(50,items.get(0).getValue());
	}	
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/business/SomeBusinessServiceTest.java

```java
package com.in28minutes.springunittestingwithmockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.in28minutes.springunittestingwithmockito.data.SomeDataService;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessServiceTest {
	
	@Mock
	SomeDataService dataService;
	
	@InjectMocks
	SomeBusinessService businessService;
	
	@Test(expected=Exception.class)
	public void testWithExpectedException() {	
		SomeDataService dataService = new SomeDataService();
		SomeBusinessService businessService = 
				new SomeBusinessService(dataService);
		businessService.calculateSum();
	}
	
	@Test
	public void testWithMock() {	
		SomeDataService dataService = mock(SomeDataService.class);
		when(dataService.retrieveData()).thenReturn(new int[] {10,20});
		SomeBusinessService businessService = 
				new SomeBusinessService(dataService);
		assertEquals(30,businessService.calculateSum());
	}

	@Test
	public void playWithListClass() {
		
	}
	
	@Test
	public void testWithMock_usingMockitoRunner() {	
		when(dataService.retrieveData()).thenReturn(new int[] {10,20});
		assertEquals(30,businessService.calculateSum());
	}
	
	@Test
	public void mockitoRunnerUnderstandSpringAutowiringToo() {
		
	}
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/controller/ItemControllerIT.java

```java
package com.in28minutes.springunittestingwithmockito.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest2() {
		String body = this.restTemplate.getForObject("/items", String.class);
		assertThat(body).contains("Pencil");
	}
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/controller/ItemControllerTest.java

```java
package com.in28minutes.springunittestingwithmockito.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.in28minutes.springunittestingwithmockito.business.ItemService;
import com.in28minutes.springunittestingwithmockito.entity.Item;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ItemService service;

	@Test
	public void retrieveItems() throws Exception {
		List<Item> mockList = Arrays.asList(new Item(1, "Dummy", 10, 5));
		when(service.calculateTotalValue()).thenReturn(mockList);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/items").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expected = "[" + "{id:1,name:Dummy}" + "]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/data/ItemRepositoryTest.java

```java
package com.in28minutes.springunittestingwithmockito.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springunittestingwithmockito.entity.Item;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ItemRepository repository;
	
	@Test
	public void testExample() throws Exception {
		List<Item> items = this.repository.findAll();
		assertThat(items.size()).isEqualTo(4);
	}

}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/HamcrestMatcherTest.java

```java
package com.in28minutes.springunittestingwithmockito;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Every.everyItem;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatcherTest {

	@Test
	public void basicHamcrestMatchers() {
		
		//List
		List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(100, 101));
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(200)));

		// String
		assertThat("", isEmptyString());

		// Array
		Integer[] marks = { 1, 2, 3 };

		assertThat(marks, arrayWithSize(3));
		assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));

	}
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/JsonAssertTest.java

```java
package com.in28minutes.springunittestingwithmockito;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {
	@Test
	public void jsonAssertTest() throws JSONException {
		String responseFromService = "[{\"id\":10001,\"name\":\"Chocolates\",\"quantity\":25,\"price\":2,\"value\":50},"
				+ "{\"id\":10002,\"name\":\"Biscuits\",\"quantity\":50,\"price\":2,\"value\":100},"
				+ "{\"id\":10003,\"name\":\"Pens\",\"quantity\":25,\"price\":3,\"value\":75},"
				+ "{\"id\":10004,\"name\":\"Pencils\",\"quantity\":25,\"price\":2,\"value\":50}]";

		JSONAssert.assertEquals("[{id:10004,name:Pencils},{},{},{}]", responseFromService, false);

		// Strict true 
		// 1. Checks all elements
		// 2. Order in arrays becomes important

		// Easy to read error messages
	}

}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/JsonPathTest.java

```java
package com.in28minutes.springunittestingwithmockito;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JsonPathTest {
	@Test
	public void jsonAssertTest() {
		String responseFromService = "[{\"id\":10001,\"name\":\"Chocolates\",\"quantity\":25,\"price\":2,\"value\":50},"
				+ "{\"id\":10002,\"name\":\"Biscuits\",\"quantity\":50,\"price\":2,\"value\":100},"
				+ "{\"id\":10003,\"name\":\"Pens\",\"quantity\":25,\"price\":3,\"value\":75},"
				+ "{\"id\":10004,\"name\":\"Pencils\",\"quantity\":25,\"price\":2,\"value\":50}]";
		
		ReadContext ctx = JsonPath.parse(responseFromService);
		
		List<Integer> allIds = ctx.read("$..id");
		assertThat(allIds).containsExactly(10001,10002,10003,10004);
		System.out.println(ctx.read("$.length()]").toString());
		System.out.println(ctx.read("$.[2]").toString());
		System.out.println(ctx.read("$.[0:2]").toString());//0 inclusive 2 exclusive
		System.out.println(ctx.read("$[?(@.quantity==50)]").toString());
	}

}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/ListTest.java

```java
package com.in28minutes.springunittestingwithmockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class ListTest {

	@Test
	public void letsMockListSize() {
		List list = mock(List.class);
		when(list.size()).thenReturn(10);
		assertEquals(10, list.size());
	}

	@Test
	public void letsMockListSizeWithMultipleReturnValues() {
		List list = mock(List.class);
		when(list.size()).thenReturn(10).thenReturn(20);
		assertEquals(10, list.size()); // First Call
		assertEquals(20, list.size()); // Second Call
	}

	@Test
	public void letsMockListGet() {
		List<String> list = mock(List.class);
		when(list.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", list.get(0));
		assertNull(list.get(1));
	}

	@Test(expected = RuntimeException.class)
	public void letsMockListGetToThrowException() {
		List<String> list = mock(List.class);
		when(list.get(Mockito.anyInt())).thenThrow(
				new RuntimeException("Something went wrong"));
		list.get(0);
	}

	@Test
	public void letsMockListGetWithAny() {
		List<String> list = mock(List.class);
		Mockito.when(list.get(Mockito.anyInt())).thenReturn("in28Minutes");
		// If you are using argument matchers, all arguments
		// have to be provided by matchers.
		assertEquals("in28Minutes", list.get(0));
		assertEquals("in28Minutes", list.get(1));
	}
}
```
---

### /src/test/java/com/in28minutes/springunittestingwithmockito/SpringUnitTestingWithMockitoApplicationTests.java

```java
package com.in28minutes.springunittestingwithmockito;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springunittestingwithmockito.business.ItemService;
import com.in28minutes.springunittestingwithmockito.data.ItemRepository;
import com.in28minutes.springunittestingwithmockito.entity.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringUnitTestingWithMockitoApplicationTests {
	
	@MockBean
	ItemRepository repository;
	
	@Autowired
	ItemService service;

	@Test
	public void contextLoads() {
		List<Item> asList = new ArrayList<Item>();
		asList.add(new Item(1, "Dummy", 10, 5));
		
		when(repository.findAll()).thenReturn(asList);

		System.out.println(service.calculateTotalValue());
	}

}
```
---
