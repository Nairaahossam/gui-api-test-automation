package com.assessment.gui.tests;

import com.assessment.gui.base.BaseTest;
import com.assessment.gui.dataproviders.TestDataProviders;
import com.assessment.gui.dataproviders.model.FileUploadTestData;
import com.assessment.gui.pages.HomePage;
import com.assessment.gui.utils.TestFileResolver;
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