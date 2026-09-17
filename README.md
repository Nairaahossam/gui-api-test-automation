# GUI Test Automation Assessment

Selenium WebDriver + TestNG UI automation suite covering two scenarios on
[the-internet.herokuapp.com](https://the-internet.herokuapp.com/):

1. **File Upload** — upload a small image and verify the success confirmation.
2. **Dynamic Loading (Example 2)** — trigger an AJAX-style load and verify the revealed text is `Hello World!`.

## Project Overview

The framework follows the **Page Object Model (POM)**.

**Main source (`src/main/java/com/assessment/gui`):**
- `base/DriverFactory.java` — creates and configures the WebDriver
- `base/BasePage.java` — shared explicit-wait helpers used by every page object
- `config/ConfigReader.java` — reads externalized `config.properties`
- `pages/HomePage.java`
- `pages/FileUploadPage.java`
- `pages/DynamicLoadingHomePage.java`
- `pages/DynamicLoadingExamplePage.java`
- `utils/JsonTestDataReader.java` — generic JSON test-data loader
- `utils/TestFileResolver.java` — resolves classpath fixtures to absolute paths

**Test source (`src/test/java/com/assessment/gui`):**
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

## Requirements

- Java 17+
- Maven 3.8+
- Google Chrome installed locally (driver binaries are downloaded automatically by WebDriverManager — nothing to install manually)

## How to Run

From the project root:

```bash
mvn clean test
```

This runs headless Chrome by default (see `config.properties`).

To see the browser while it runs, edit `config.properties` and set `headless=false`.

### Test reports

TestNG generates results under `target/surefire-reports/` after each run.