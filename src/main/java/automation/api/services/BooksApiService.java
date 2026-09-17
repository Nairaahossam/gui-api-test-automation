package automation.api.services;

import automation.api.models.Book;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BooksApiService extends BaseApiService {

    private static final String BOOKS_ENDPOINT = "/api/v1/Books";
    private static final String BOOK_BY_ID_ENDPOINT = BOOKS_ENDPOINT + "/{id}";

    public Response getAllBooks() {
        return given()
                .spec(requestSpec)
                .when()
                .get(BOOKS_ENDPOINT);
    }

    public Response getBookById(int id) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .get(BOOK_BY_ID_ENDPOINT);
    }

    public Response createBook(Book book) {
        return given()
                .spec(requestSpec)
                .body(book)
                .when()
                .post(BOOKS_ENDPOINT);
    }

    public Response updateBook(int id, Book book) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .body(book)
                .when()
                .put(BOOK_BY_ID_ENDPOINT);
    }

    public Response deleteBook(int id) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .delete(BOOK_BY_ID_ENDPOINT);
    }
}
