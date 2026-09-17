package com.assessment.gui.pages;

import com.assessment.gui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicLoadingExamplePage extends BasePage {

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(id = "finish")
    private WebElement finishText;

    private static final By LOADING_INDICATOR = By.id("loading");

    public DynamicLoadingExamplePage(WebDriver driver) {
        super(driver);
    }

    public DynamicLoadingExamplePage clickStart() {
        waitForClickable(startButton).click();
        return this;
    }

    public String waitForFinishTextAndGet() {
        waitForInvisible(LOADING_INDICATOR);
        waitForVisible(finishText);
        return finishText.getText();
    }
}