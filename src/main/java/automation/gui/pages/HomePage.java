package automation.gui.pages;

import automation.gui.base.BasePage;
import automation.gui.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "File Upload")
    private WebElement fileUploadLink;

    @FindBy(linkText = "Dynamic Loading")
    private WebElement dynamicLoadingLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage navigateTo() {
        driver.get(ConfigReader.getBaseUrl() + "/");
        waitForVisible(fileUploadLink);
        return this;
    }

    public FileUploadPage goToFileUpload() {
        waitForClickable(fileUploadLink).click();
        return new FileUploadPage(driver);
    }

    public DynamicLoadingHomePage goToDynamicLoading() {
        waitForClickable(dynamicLoadingLink).click();
        return new DynamicLoadingHomePage(driver);
    }
}