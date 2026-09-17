package com.assessment.gui.pages;

import com.assessment.gui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingHomePage extends BasePage {

    public DynamicLoadingHomePage(WebDriver driver) {
        super(driver);
    }

    public DynamicLoadingExamplePage selectExample(String exampleNumber) {
        By exampleLink = By.partialLinkText("Example " + exampleNumber);
        waitForClickable(waitForVisible(exampleLink)).click();
        return new DynamicLoadingExamplePage(driver);
    }
}