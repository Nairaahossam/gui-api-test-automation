package automation.api.dataproviders.model;

public class BookLookupTestData {

    private String scenario;
    private int bookId;

    public String getScenario() {
        return scenario;
    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return scenario;
    }
}
