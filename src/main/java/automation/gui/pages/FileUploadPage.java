package automation.gui.pages;

import automation.gui.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FileUploadPage extends BasePage {

    @FindBy(id = "file-upload")
    private WebElement fileInput;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFileNameLabel;

    @FindBy(css = "div.example h3")
    private WebElement pageHeading;

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    public FileUploadPage waitUntilLoaded() {
        waitForVisible(pageHeading);
        return this;
    }

    public FileUploadPage uploadFile(String absoluteFilePath) {
        waitForVisible(fileInput).sendKeys(absoluteFilePath);
        return this;
    }

    public FileUploadPage submit() {
        waitForClickable(uploadButton).click();
        waitForVisible(uploadedFileNameLabel);
        return this;
    }

    public String getUploadedFileName() {
        return uploadedFileNameLabel.getText();
    }
}