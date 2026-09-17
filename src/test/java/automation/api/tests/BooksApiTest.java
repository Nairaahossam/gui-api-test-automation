package automation.api.tests;

import automation.api.base.BaseApiTest;
import automation.api.dataproviders.ApiTestDataProviders;
import automation.api.dataproviders.model.BookLookupTestData;
import automation.api.dataproviders.model.BookNotFoundTestData;
import automation.api.dataproviders.model.CreateBookTestData;
import automation.api.models.Book;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class BooksApiTest extends BaseApiTest {

    @Test(
            dataProvider = "bookLookupData",
            dataProviderClass = ApiTestDataProviders.class,
            description = "Fetching an existing book by id returns 200 with the matching book"
    )
    public void gettingAnExistingBookReturnsTheMatchingBook(BookLookupTestData data) {
        Book book = getBooksApiService()
                .getBookById(data.getBookId())
                .then()
                .statusCode(200)
                .extract()
                .as(Book.class);

        Assert.assertEquals(book.getId(), Integer.valueOf(data.getBookId()),
                "The returned book id should match the requested id");
        Assert.assertNotNull(book.getTitle(), "The returned book should have a title");
    }

    @Test(
            dataProvider = "createBookData",
            dataProviderClass = ApiTestDataProviders.class,
            description = "Creating a book with valid data echoes back the submitted book"
    )
    public void creatingABookWithValidDataReturnsTheSubmittedBook(CreateBookTestData data) {
        Book newBook = Book.builder()
                .id(data.getId())
                .title(data.getTitle())
                .description(data.getDescription())
                .pageCount(data.getPageCount())
                .excerpt(data.getExcerpt())
                .publishDate(data.getPublishDate())
                .build();

        Book createdBook = getBooksApiService()
                .createBook(newBook)
                .then()
                .statusCode(200)
                .extract()
                .as(Book.class);

        Assert.assertEquals(createdBook.getId(), newBook.getId(),
                "The created book id should match the submitted id");
        Assert.assertEquals(createdBook.getTitle(), newBook.getTitle(),
                "The created book title should match the submitted title");
        Assert.assertEquals(createdBook.getPageCount(), newBook.getPageCount(),
                "The created book page count should match the submitted page count");
    }

    @Test(
            dataProvider = "bookNotFoundData",
            dataProviderClass = ApiTestDataProviders.class,
            description = "Fetching a book id that does not exist returns 404 Not Found"
    )
    public void gettingANonExistentBookReturnsNotFound(BookNotFoundTestData data) {
        Response response = getBooksApiService().getBookById(data.getInvalidBookId());

        response.then()
                .statusCode(404)
                .body("title", equalTo("Not Found"));
    }
}
