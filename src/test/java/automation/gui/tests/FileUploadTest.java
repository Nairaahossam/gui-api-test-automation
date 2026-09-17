package automation.gui.tests;

import automation.gui.base.BaseTest;
import automation.gui.dataproviders.TestDataProviders;
import automation.gui.dataproviders.model.FileUploadTestData;
import automation.gui.pages.HomePage;
import automation.gui.utils.TestFileResolver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @Test(
            dataProvider = "fileUploadData",
            dataProviderClass = TestDataProviders.class,
            description = "Uploading a valid small image succeeds and the uploaded file name is displayed"
    )
    public void uploadedFileNameIsDisplayedAfterSuccessfulUpload(FileUploadTestData data) {
        String absoluteFilePath = TestFileResolver.resolveAbsolutePath("testfiles/" + data.getFileName());

        String uploadedFileName = new HomePage(getDriver())
                .navigateTo()
                .goToFileUpload()
                .waitUntilLoaded()
                .uploadFile(absoluteFilePath)
                .submit()
                .getUploadedFileName();

        Assert.assertEquals(
                uploadedFileName,
                data.getFileName(),
                "The file name confirmed on the upload success page should match the file that was submitted");
    }
}