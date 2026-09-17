package automation.api.base;

import automation.api.services.BooksApiService;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    private BooksApiService booksApiService;

    @BeforeClass(alwaysRun = true)
    public void setUpService() {
        booksApiService = new BooksApiService();
    }

    protected BooksApiService getBooksApiService() {
        return booksApiService;
    }
}
