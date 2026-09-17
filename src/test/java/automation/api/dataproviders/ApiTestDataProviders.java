package automation.api.dataproviders;

import automation.api.dataproviders.model.BookLookupTestData;
import automation.api.dataproviders.model.BookNotFoundTestData;
import automation.api.dataproviders.model.CreateBookTestData;
import automation.common.utils.JsonTestDataReader;
import org.testng.annotations.DataProvider;

import java.util.List;

public final class ApiTestDataProviders {

    private ApiTestDataProviders() {
    }

    @DataProvider(name = "bookLookupData")
    public static Object[][] bookLookupData() {
        List<BookLookupTestData> rows = JsonTestDataReader.readList(
                "testdata/books-lookup-data.json", BookLookupTestData.class);
        return rows.stream().map(row -> new Object[]{row}).toArray(Object[][]::new);
    }

    @DataProvider(name = "createBookData")
    public static Object[][] createBookData() {
        List<CreateBookTestData> rows = JsonTestDataReader.readList(
                "testdata/books-create-data.json", CreateBookTestData.class);
        return rows.stream().map(row -> new Object[]{row}).toArray(Object[][]::new);
    }

    @DataProvider(name = "bookNotFoundData")
    public static Object[][] bookNotFoundData() {
        List<BookNotFoundTestData> rows = JsonTestDataReader.readList(
                "testdata/books-not-found-data.json", BookNotFoundTestData.class);
        return rows.stream().map(row -> new Object[]{row}).toArray(Object[][]::new);
    }
}
