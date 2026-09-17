package automation.gui.tests;

import automation.gui.base.BaseTest;
import automation.gui.dataproviders.TestDataProviders;
import automation.gui.dataproviders.model.DynamicLoadingTestData;
import automation.gui.pages.HomePage;
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