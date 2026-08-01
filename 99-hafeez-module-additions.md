# Spring Boot Unit Testing - Additions (Steps 27 to 33)

These are the **new** steps added on top of the existing Steps 01 to 26. Nothing existing was removed or reordered - everything here sits at the end of the section.

**What Steps 01 to 26 already covered**
- MockMvc with `RequestBuilder`, `andExpect(status())`, `content().json()`
- The test slices - `@WebMvcTest`, `@DataJpaTest`, `@SpringBootTest`
- Mocking a Spring bean with `@MockitoBean`, mocking a plain dependency with `@Mock` and `@InjectMocks`
- The assertion libraries as standalone spikes - Hamcrest, AssertJ, JSONassert, JsonPath
- Test coverage in Eclipse, test performance, what makes a good unit test

**What is new**

| Step | Topic |
|---|---|
| Step 27 | JUnit 6 assertions - `@DisplayName`, `assertThrows`, `assertAll`, `@Nested`, `@Timeout` |
| Step 28 | `@ParameterizedTest` - `@ValueSource`, `@CsvSource`, `@MethodSource` |
| Step 29 | `MockMvcTester` - AssertJ native controller tests |
| Step 30 | `@JsonTest` and `JacksonTester` - testing serialization on its own |
| Step 31 | `@MockitoSpyBean` and `@TestBean` - the other two bean overrides |
| Step 32 | `RestTestClient` with its own assertions |
| Step 33 | Coverage with JaCoCo, and integration tests that run in the build |

**Why these steps were needed.** Three gaps, each confirmed against the resolved dependency tree:

1. **The course says JUnit 5, the build resolves JUnit Jupiter 6.0.1.** And across 26 steps the JUnit surface is `@Test` and `assertEquals`. No parameterized tests, no `assertThrows`, no `@Nested`.
2. **`MockMvcTester` is auto-configured and never used.** `@WebMvcTest` wires it up whenever AssertJ is on the classpath, which it always is.
3. **`ItemControllerIT` never ran.** Surefire's default includes are `**/Test*.java`, `**/*Test.java`, `**/*Tests.java` and `**/*TestCase.java` - `*IT` matches none of them. The integration test from Step 15 has been passing in the IDE and silently skipped by `mvn test`.

Worth saying on the recording: none of Steps 27, 28 or 31 is exotic. The `@API` metadata in the jars puts `@Nested` at stable since JUnit 5.0 and `@ParameterizedTest` since 5.7; `@TestBean` arrived in Spring Framework 6.2. They were never covered here. Only `MockMvcTester` and `RestTestClient` are genuinely recent.

**Versions this was built against**, from `mvn dependency:tree`:

| Library | Version |
|---|---|
| Spring Boot | 4.0.0 |
| spring-test | 7.0.1 |
| JUnit Jupiter | 6.0.1 |
| Mockito | 5.20.0 |
| AssertJ | 3.27.6 |
| Hamcrest | 3.0 |
| JSONassert | 1.5.3 |
| JsonPath | 2.9.0 |

---

## Changes to existing code

**`SomeDataService` gained a `void` method**, so Step 27 has something to verify.

```java
public interface SomeDataService {

	int[] retrieveAllData();

	void storeSum(int sum);

}
```

```java
public void storeSumUsingDataService() {
	someDataService.storeSum(calculateSumUsingDataService());
}
```

**All three handwritten stubs had to implement it.** That is useful for **Step 04** - that step claims stubs are a maintenance burden, and it can now show it live. On screen: *"I added one method to the interface and every stub class went red. A mock needs no such change."*

**`ListMockTest` uses `mock()` type inference** instead of raw `List` and `ArrayList`, so no more unchecked warnings on screen.

**`spring.jpa.open-in-view=false`** in both `application.properties` files. It removes a `WARN` line from every Spring context startup.

**The dead `//someDataService.storeSum(sum);` comment** was removed from `calculateSumUsingDataService` - there is a real `storeSum` now, so the placeholder is more confusing than useful.

> **Note on the code snapshots.** `00.code-after-preparation.md`, `01.code-after-integration-test.md` and `02.Final.md` capture the code as of **Steps 01 to 26** and deliberately predate everything above - their `SomeDataService` has no `storeSum` and their stubs do not implement it. They are still correct as snapshots. Copy a *whole* file from them and the project builds; copy `SomeDataService.java` alone into the current tree and `SomeBusinessStubTest` will not compile.

## Dependencies

**No new test libraries.** `@ParameterizedTest`, `MockMvcTester`, `@JsonTest`, `@TestBean` and `RestTestClient` are all already on the classpath through the existing starters.

**Four build plugins**, three of which exist only to keep the console clean or to run tests that were being skipped.

```xml
<properties>
	<jacoco.version>0.8.15</jacoco.version>
</properties>
```

```xml
<!-- Exposes dependency paths as properties, so surefire can point -javaagent at the Mockito jar -->
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-dependency-plugin</artifactId>
	<executions>
		<execution>
			<goals>
				<goal>properties</goal>
			</goals>
		</execution>
	</executions>
</plugin>

<!-- Loads Mockito as an agent at startup - JEP 451 - instead of letting it self-attach -->
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<configuration>
		<argLine>@{argLine} -javaagent:"${org.mockito:mockito-core:jar}" -Xshare:off</argLine>
	</configuration>
</plugin>

<!-- Surefire ignores *IT classes. Without failsafe, ItemControllerIT never runs in the build. -->
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-failsafe-plugin</artifactId>
	<configuration>
		<argLine>@{argLine} -javaagent:"${org.mockito:mockito-core:jar}" -Xshare:off</argLine>
	</configuration>
	<executions>
		<execution>
			<goals>
				<goal>integration-test</goal>
				<goal>verify</goal>
			</goals>
		</execution>
	</executions>
</plugin>

<!-- mvn verify -> target/site/jacoco/index.html -->
<plugin>
	<groupId>org.jacoco</groupId>
	<artifactId>jacoco-maven-plugin</artifactId>
	<version>${jacoco.version}</version>
	<executions>
		<execution>
			<id>prepare-agent</id>
			<goals>
				<goal>prepare-agent</goal>
			</goals>
		</execution>
		<execution>
			<id>report</id>
			<phase>verify</phase>
			<goals>
				<goal>report</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

Three details worth a sentence each on the recording:

- Mockito used to self-attach its Java agent at runtime, which printed a notice plus five warning lines on every run. [JEP 451](https://openjdk.org/jeps/451) asks libraries to load the agent at startup instead. `-Xshare:off` silences the class data sharing warning that appending to the bootstrap classpath causes.
- `@{argLine}` is **late replacement**, available since Surefire 2.17. Maven resolves `${...}` before any plugin runs, so `${argLine}` would be substituted before JaCoCo ever sets it and coverage would silently record nothing. `@{...}` is read when surefire executes. `-Djacoco.skip=true` stays safe for a different reason: `prepare-agent` is skipped and never sets the property at all, and Surefire replaces an unresolvable `@{...}` with the empty string - which is exactly what `${...}` does *not* do.
- The `-javaagent` path is quoted, so it survives a local repository under a folder with a space in the name.

## Two Boot 4 migration points these steps rely on

Both are already handled in the code. Neither is obvious from reading it, and both are the kind of thing that breaks a student's own upgrade, so they are worth naming out loud.

**`@SpringBootTest` no longer hands you an HTTP client.** In Boot 3, `@SpringBootTest(webEnvironment = RANDOM_PORT)` gave you a `TestRestTemplate` bean for free. Boot 4 provides neither `TestRestTemplate` nor `WebClient` by default. That is why both integration tests carry `@AutoConfigureRestTestClient`, and why the pom needs `spring-boot-starter-restclient-test`. Remove either and the `@Autowired RestTestClient` fails to inject. [Migration guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide#using-webclient-or-testresttemplate-and-springboottest)

**`MockitoTestExecutionListener` is gone.** Boot used to process `@Mock` and `@Captor` through its own listener, so those fields worked in a Spring test without anything else. It was deprecated in 3.4 and removed in 4.0 - Mockito's own `@ExtendWith(MockitoExtension.class)` is now the only thing that initialises them. `SomeBusinessMockTest`, `ItemBusinessServiceTest` and `SomeBusinessAssertionsTest` all declare it. Drop the annotation and every `@Mock` field is silently `null`. [Migration guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide#upgrading-testing-features)

---

### **Step 27 - Beyond assertEquals**

Steps 20 to 22 introduce Hamcrest, AssertJ and JsonPath, but as standalone spikes. Every test that exercises the application itself asserts with `assertEquals` and nothing else. Four JUnit assertions fill that gap, and they take ten minutes together.

```java
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
		//@Timeout fails the test instead of hanging the build if this ever starts blocking.
		@Test
		@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
		@DisplayName("stores the sum it just calculated, well inside half a second")
		void storesTheCalculatedSum() {
			when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 1, 2, 3 });

			business.storeSumUsingDataService();

			verify(dataServiceMock).storeSum(6);
		}
	}
}
```

**Things to point out**

- **Live demo for `assertAll`:** change two of the three expected values and run. All three assertions are evaluated and both failures are reported together. Written as three plain `assertEquals` calls, the method would stop at the first and hide the second - that contrast is the whole reason `assertAll` exists.
- `assertThrows` **returns** the exception, so `assertThrows(...).getMessage()` is available when the message matters.
- `@Nested` is what makes the tree in the IDE readable - `SomeBusinessImpl > calculateSum > adds up every value in the array`. Show the runner panel, not the code.
- `@DisplayName` is the sentence a non-Java reader can follow. Method names stay for the compiler.
- Tests are **package-private**. The JUnit guide is explicit - test classes, methods and lifecycle methods *"are not required to be `public`, but they must not be `private`"* - and it recommends dropping `public` unless there is a technical reason. Steps 01 to 26 are left as they are; that is 26 steps of screenshots to reshoot otherwise.
- **One caveat if this ever runs in CI.** The four tests here execute and are counted in Surefire's console total, but Surefire writes no XML report for a `@Nested` class - `TEST-...SomeBusinessAssertionsTest.xml` records `tests="0"`. So the console says 56 while `target/surefire-reports/*.xml` sums to 52. Any tool that reads those XML files - Jenkins, a GitHub Actions test reporter, Sonar - will under-report by four. It is a Surefire reporting limitation, not a skipped test; overriding Surefire's `**/*$*` exclude does not change it.

[JUnit User Guide](https://docs.junit.org/current/user-guide/) | [Nested Tests](https://docs.junit.org/current/writing-tests/nested-tests.html)

---

### **Step 28 - One Parameterized Test Replaces Three**

`calculateSum_basic`, `calculateSum_empty` and `calculateSum_oneValue` are the same test three times with different data. That is exactly what `@ParameterizedTest` is for.

```java
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
```

**Things to point out**

- **Three methods, ten test runs.** Show the IDE runner - each row is a separate pass or fail, so one bad case does not hide the rest.
- The `name` attribute is what makes those rows readable. Without it you get `[1]`, `[2]`, `[3]`.
- **How to choose:** one primitive per run -> `@ValueSource`. A handful of primitives -> `@CsvSource`. Anything an annotation cannot hold, like an array or an `Item` -> `@MethodSource`.
- `@MethodSource` resolves the factory by method name. Here it is `static` and returns a `Stream<Arguments>`; `Iterable` and arrays work too.
- The `-5, 5, 0` row in `@CsvSource` costs one line and covers negatives, which the original three tests never did.

[Parameterized Classes and Tests](https://docs.junit.org/current/writing-tests/parameterized-classes-and-tests.html)

---

### **Step 29 - MockMvcTester - MockMvc with AssertJ**

Same endpoints as `ItemControllerTest`, same slice, same mock. Only the assertion style changes - and `throws Exception` disappears.

```java
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
```

**Things to point out**

- Put this class next to `ItemControllerTest` on screen. `dummyItem_basic` is four lines here against eight there, and the eight include a `RequestBuilder` and an `MvcResult` that nothing ever reads.
- **`throws Exception` is gone.** `mvc.perform(...)` declares it; `MockMvcTester` does not.
- **Far fewer static imports.** Steps 02 to 08 need `status()`, `content()` and `MockMvcRequestBuilders` before anything compiles. This needs `assertThat`, plus Mockito's `when` on the tests that stub.
- `isLenientlyEqualTo` is the JSONassert `strict=false` from Step 06, spelled out in words.
- `hasPathSatisfying` is JSONPath from Step 22, inline in the assertion instead of a separate spike test.
- Nothing to install. **Watch the import** - it is `org.springframework.test.web.servlet.assertj.MockMvcTester`, and the `assertj` package is easy to miss.

[MockMvc AssertJ support](https://docs.spring.io/spring-framework/reference/testing/mockmvc/assertj.html) | [MockMvcTester javadoc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/assertj/MockMvcTester.html)

---

### **Step 30 - @JsonTest and JacksonTester - Testing Serialization on Its Own**

Step 05 tests `Item`'s JSON by starting a web context and firing a request at a controller. If the only question is *"does `Item` serialize correctly"*, that is a lot of machinery for the answer.

```java
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
```

**Things to point out**

- **The second test is the point of the step.** `@Transient` keeps `value` out of the database, and it is easy to assume it keeps the field out of the JSON too. It does not. Jackson sees `getValue()` and writes `value`. The whole class runs in under a tenth of a second.
- `isStrictlyEqualToJson` means every field must be present and match. Use it when the response shape is the contract.
- **Say what the slice leaves out, not just what it includes.** Asking the context directly: zero `ItemController`, zero `ItemBusinessService`, zero `ItemRepository`, zero `DataSource` beans. **32 bean definitions against 236 for a `@SpringBootTest`** in this same project - that ratio is the reason slices exist.
- Boot 4 moved to Jackson 3: packages are `tools.jackson.*`, not `com.fasterxml.jackson.*` (the `jackson-annotations` module is the exception and keeps its old package). None of it shows up in this test, but it will the moment anyone writes a custom serializer - `@JsonComponent` is now `@JacksonComponent`, and `@JsonMixin` is `@JacksonMixin`.

[@JsonTest javadoc](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/autoconfigure/json/JsonTest.html) | [JacksonTester](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/json/JacksonTester.html)

---

### **Step 31 - The Other Two Bean Overrides**

Step 16 covers `@MockitoBean`. There are three bean override annotations, and the other two answer questions `@MockitoBean` cannot.

**`@MockitoSpyBean` - keep the real bean, still verify it**

```java
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
```

**`@TestBean` - your own object, no Mockito at all**

```java
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
```

**Things to point out**

- The one-line summary: **`@MockitoBean` replaces, `@MockitoSpyBean` wraps, `@TestBean` substitutes.**
- The spy test is worth pausing on - `assertThat(items).hasSize(3)` proves the **real** repository ran and returned the three rows from `data.sql`, and `verify` still works on it. A `@MockitoBean` could be verified just as easily, but it would return whatever you stubbed, never the real rows.
- **`@TestBean` resolves the factory method by field name.** Rename the field and forget the method, and the failure message names the method it looked for - show that failure once.
- The factory method must be `static` and take no arguments. Any visibility is fine.
- All three live in `spring-test`, under `org.springframework.test.context.bean.override`, added in Spring Framework 6.2. Boot's own `@MockBean` and `@SpyBean` are **removed** in Boot 4, not merely deprecated - deprecated in 3.4, gone in 4. The classes are not in the jars any more.

[Bean Overriding in Tests](https://docs.spring.io/spring-framework/reference/testing/testcontext-framework/bean-overriding.html) | [@TestBean](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-testbean.html) | [@MockitoBean and @MockitoSpyBean](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-mockitobean.html)

---

### **Step 32 - RestTestClient, Using Its Own Assertions**

`ItemControllerIT` from Step 15 already uses `RestTestClient`, but reads the body back as a `byte[]`, wraps it in a `String` and hands it to `JSONAssert`. `RestTestClient` has assertions of its own.

```java
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

	//jsonPath drills into single values, so the whole payload does not have to be spelled out.
	//findAll() has no ORDER BY, so $..name collects every name rather than indexing into $[0].
	@Test
	void retrieveAllItems_individualValues() {
		restTestClient.get().uri("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.length()").isEqualTo(3)
				.jsonPath("$..name").value((List<String> names) ->
						assertThat(names).containsExactlyInAnyOrder("Item1", "Item2", "Item3"))
				.jsonPath("$..value").value((List<Integer> values) ->
						assertThat(values).containsExactlyInAnyOrder(200, 50, 30));
	}

	@Test
	void unknownUri_isNotFound() {
		restTestClient.get().uri("/no-such-endpoint")
				.exchange()
				.expectStatus().isNotFound();
	}
}
```

**Things to point out**

- Show the old `new String(...getResponseBodyContent())` line, then this. Same test, no plumbing.
- `.json(...)` is lenient by default - the same behaviour as `JSONAssert.assertEquals(expected, actual, false)` from Step 06.
- **This is a real HTTP call** on a random port, through the real controller, business service, repository and H2. The values come back as `200`, `50` and `30` because `ItemBusinessService` multiplied price by quantity on every row of `data.sql` - every layer ran.
- **Why `$..name` and not `$[0].name`.** `findAll()` carries no `ORDER BY`, so nothing in JPA, H2 or the HTTP layer promises the rows arrive in insert order. Indexing into `$[0]` passes today and can fail on another machine or another H2 version. `$..` collects every match and the assertion compares them as a set. Same point in `ItemBusinessServiceSpyBeanTest`. Worth thirty seconds on the recording - order dependence is one of the most common causes of a flaky test.
- `TestRestTemplate` still ships in Boot 4, but it moved to `org.springframework.boot.resttestclient` and now needs its own dependency. It is not yet marked `@Deprecated`; `RestTestClient` is the direction of travel, in step with `RestTemplate` giving way to `RestClient`.

[RestTestClient javadoc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/client/RestTestClient.html) | [The state of HTTP clients in Spring](https://spring.io/blog/2025/09/30/the-state-of-http-clients-in-spring)

---

### **Step 33 - Coverage with JaCoCo, and the Tests That Were Not Running**

Two build problems, one step.

**The integration test was never running.** Surefire's default includes are `Test*`, `*Test`, `*Tests` and `*TestCase`. `ItemControllerIT` matches none of them. It has been green in the IDE and skipped by Maven this whole time. `maven-failsafe-plugin` is the piece that runs `*IT` classes, and it binds to `verify`, after the application has been packaged.

```
mvn test      -> 56 tests, unit tests only
mvn verify    -> 56 + 4 integration tests, then the coverage report
```

**Coverage without the IDE.** Step 24 uses `Run As > Coverage As > JUnit Test` in Eclipse. That works, but it is Eclipse-only and cannot run in CI. JaCoCo produces the same report from the command line.

```
mvn clean verify
open target/site/jacoco/index.html
```

**Things to point out**

- **Demo the failsafe fix first.** Run `mvn test`, count the classes in the output, point out `ItemControllerIT` is missing. Add the plugin, run `mvn verify`, it appears. A naming convention silently skipping a test is a mistake worth showing.
- The JaCoCo report is the same red and green as Eclipse, in a browser, and it survives in CI as a build artifact.
- **The number to show is 91%, and then open the report and read the four red lines.** Two are `main()`. One is `Item.toString()`. The fourth is the interesting one:

  ```
  ItemBusinessService.retreiveHardcodedItem()
      return new Item(1, "Ball", 10, 100);   // never executed
  ```

  Two tests exercise the endpoint that calls it - `ItemControllerTest` and `ItemControllerMockMvcTesterTest` - and both mock `ItemBusinessService`, so the real line has never run. **That is what a coverage report is for** - not the percentage, but finding the method everybody assumed was tested. Tie it back to Step 24: coverage tells you what was never executed and nothing at all about whether the assertions were any good.
- **Pin the JaCoCo version to your JDK, not to a blog post.** It instruments bytecode, so it has to understand both the class files and the JVM. `java.version` in the pom is 25, so the classes are Java 25 bytecode - but Maven itself was running on JDK 26.0.1 here, and 0.8.15 is the first release with official Java 26 support. Worth a `mvn -version` on screen: the JDK Maven runs on is not always the one on your `PATH`.

[JaCoCo Maven plugin](https://www.jacoco.org/jacoco/trunk/doc/maven.html) | [JaCoCo change history](https://www.jacoco.org/jacoco/trunk/doc/changes.html) | [maven-failsafe-plugin](https://maven.apache.org/surefire/maven-failsafe-plugin/)

---

## Not included, and why

- **Testcontainers.** A real PostgreSQL beats H2 for integration tests, but it needs Docker running while you record. Too fragile for a beginner module, and H2 already makes the teaching point. Worth naming as the next step for anyone going to production - `spring-boot-testcontainers` plus `@ServiceConnection` wires a container to the datasource with no property plumbing at all. [Testcontainers reference](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)
- **Awaitility.** Ships in the starter at 4.3.0, but there is no async code here to wait on. Nothing to demonstrate.
- **`@ParameterizedClass`.** Added in JUnit 5.13 and genuinely useful, but still marked `EXPERIMENTAL` in the `@API` metadata of the 6.0.1 jars, and it is the advanced form of Step 28. Name it, do not demo it.
- **PowerMock.** Mockito 5 makes the inline mock maker the default, so static and final mocking need no extra dependency.
- **Upgrading past Spring Boot 4.0.0.** 4.1.0 went GA on 10 June 2026 and removes everything 4.0 deprecated, so the upgrade is worth planning. It is a separate decision from these steps - flagging it, not doing it here.

---

**Verification:** `mvn clean verify` from the project root.

```
Tests run: 56, Failures: 0, Errors: 0, Skipped: 1   (surefire)
Tests run: 4,  Failures: 0, Errors: 0, Skipped: 0   (failsafe)
BUILD SUCCESS
```

The one skipped test is the pre-existing `@Disabled` in `ListMockTest`. The console has no Mockito self-attach notice, no JVM agent warnings and no `open-in-view` warning. Coverage lands at **91%** of lines.

References: [What's new for testing in Spring Boot 4 and Spring Framework 7](https://rieckpil.de/whats-new-for-testing-in-spring-boot-4-0-and-spring-framework-7/) | [Spring Boot testing reference](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html) | [Spring Boot 4.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide)
