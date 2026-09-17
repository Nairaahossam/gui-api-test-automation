package com.assessment.gui.tests;

import com.assessment.gui.base.BaseTest;
import com.assessment.gui.dataproviders.TestDataProviders;
import com.assessment.gui.dataproviders.model.DynamicLoadingTestData;
import com.assessment.gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTest extends BaseTest {

    @Test(
            dataProvider = "dynamicLoadingData",
            dataProviderClass = TestDataProviders.class,
            description = "After the loading indicator disappears, the expected finish text is displayed"
    )
    public void finishTextIsDisplayedAfterDynamicLoadCompletes(DynamicLoadingTestData data) {
        String actualText = new HomePage(getDriver())
                .navigateTo()
                .goToDynamicLoading()
                .selectExample(data.getExampleNumber())
                .clickStart()
                .waitForFinishTextAndGet();

        Assert.assertEquals(
                actualText,
                data.getExpectedText(),
                "The text revealed once dynamic loading finishes should match the expected copy");
    }
}