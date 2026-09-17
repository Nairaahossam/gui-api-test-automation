package automation.api.dataproviders.model;

public class BookNotFoundTestData {

    private String scenario;
    private int invalidBookId;

    public String getScenario() {
        return scenario;
    }

    public int getInvalidBookId() {
        return invalidBookId;
    }

    @Override
    public String toString() {
        return scenario;
    }
}
