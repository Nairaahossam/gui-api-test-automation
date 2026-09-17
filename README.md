# QC Automation Assessment

A single Maven project containing both the **GUI** and **API** automation assessments,
sharing one `config.properties` and one `testng.xml` suite.

- **GUI** — Selenium WebDriver + TestNG, covering two scenarios on
  [the-internet.herokuapp.com](https://the-internet.herokuapp.com/).
- **API** — REST Assured + TestNG, covering the Books resource of the
  [FakeRESTApi](https://fakerestapi.azurewebsites.net/) bookstore API.

## GUI Test Automation Assessment

1. **File Upload** — upload a small image and verify the success confirmation.
2. **Dynamic Loading (Example 2)** — trigger an AJAX-style load and verify the revealed text is `Hello World!`.

### Project Overview

The framework follows the **Page Object Model (POM)**.

**Main source (`src/main/java/automation/gui`):**
- `base/DriverFactory.java` — creates and configures the WebDriver
- `base/BasePage.java` — shared explicit-wait helpers used by every page object
- `config/ConfigReader.java` — reads externalized `config.properties`
- `pages/HomePage.java`
- `pages/FileUploadPage.java`
- `pages/DynamicLoadingHomePage.java`
- `pages/DynamicLoadingExamplePage.java`
- `utils/TestFileResolver.java` — resolves classpath fixtures to absolute paths

**Test source (`src/test/java/automation/gui`):**
- `base/BaseTest.java` — TestNG lifecycle, ThreadLocal driver (parallel-safe)
- `dataproviders/TestDataProviders.java`
- `dataproviders/model/` — DTOs mapped from JSON test data
- `tests/FileUploadTest.java`
- `tests/DynamicLoadingTest.java`

Page objects expose **fluent methods**, for example:

```java
homePage.navigateTo().goToFileUpload().uploadFile(path).submit();
```

Synchronization uses `WebDriverWait` / `ExpectedConditions` exclusively — there are no `Thread.sleep()` calls anywhere in the framework.

Test data (file name to upload, which "Example" to run, expected text) is externalized to JSON under `src/test/resources/testdata` and wired into the tests through TestNG `@DataProvider`s.

## API Test Automation Assessment

Covers the FakeRESTApi Books endpoints (`GET /api/v1/Books`, `GET /api/v1/Books/{id}`,
`POST /api/v1/Books`, `PUT /api/v1/Books/{id}`, `DELETE /api/v1/Books/{id}`):

1. **Happy path** — fetching an existing book by id returns `200` with a body matching that id.
2. **Happy path** — creating a book with valid data returns `200` and echoes back the submitted fields.
3. **Negative** — fetching a book id that doesn't exist returns `404 Not Found`.

> **Note on FakeRESTApi behaviour:** the API does not persist data. `POST`/`PUT` simply
> validate and echo back whatever payload is submitted (confirmed by re-fetching a book
> immediately after creating/updating it — the original data is unchanged), and `DELETE`
> always responds `200` regardless of whether the id exists. Only `GET /api/v1/Books/{id}`
> for an id outside the seeded range 1–200 reliably returns `404`, which is why it was
> chosen as the negative scenario.

### Project Overview

The framework follows a **Service Object Model**, mirroring the GUI suite's POM structure.

**Main source (`src/main/java/automation/api`):**
- `config/ApiConfigReader.java` — reads the API base URL from `config.properties`
- `services/BaseApiService.java` — builds the shared REST Assured `RequestSpecification`
- `services/BooksApiService.java` — fluent, reusable Books endpoint operations
- `models/Book.java` — request/response POJO with a fluent `Builder` for constructing test payloads

**Test source (`src/test/java/automation/api`):**
- `base/BaseApiTest.java` — TestNG lifecycle, provisions the `BooksApiService`
- `dataproviders/ApiTestDataProviders.java`
- `dataproviders/model/` — DTOs mapped from JSON test data
- `tests/BooksApiTest.java`

Tests call the service layer fluently, for example:

```java
Book book = getBooksApiService()
        .getBookById(data.getBookId())
        .then()
        .statusCode(200)
        .extract()
        .as(Book.class);
```

Test data (book ids to look up, payloads to create) is externalized to JSON under
`src/test/resources/testdata` and wired into the tests through TestNG `@DataProvider`s.

## Requirements

- Java 17+
- Maven 3.8+
- Google Chrome installed locally for the GUI suite (driver binaries are downloaded automatically by WebDriverManager — nothing to install manually)
- Internet access to reach `the-internet.herokuapp.com` and `fakerestapi.azurewebsites.net`

## How to Run

From the project root, run everything (GUI + API):

```bash
mvn clean test
```

This runs headless Chrome by default (see `config.properties`). To see the browser while it runs, edit `config.properties` and set `headless=false`.

To run only one suite:

```bash
mvn test -Dtest=FileUploadTest,DynamicLoadingTest   # GUI only
mvn test -Dtest=BooksApiTest                        # API only
```

### Test reports

TestNG generates results under `target/surefire-reports/` after each run.
