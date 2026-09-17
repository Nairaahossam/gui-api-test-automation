package automation.api.dataproviders.model;

public class CreateBookTestData {

    private String scenario;
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;

    public String getScenario() {
        return scenario;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getPublishDate() {
        return publishDate;
    }

    @Override
    public String toString() {
        return scenario;
    }
}
