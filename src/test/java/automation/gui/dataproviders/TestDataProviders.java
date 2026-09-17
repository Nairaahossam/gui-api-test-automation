package automation.gui.dataproviders;

import automation.gui.dataproviders.model.DynamicLoadingTestData;
import automation.gui.dataproviders.model.FileUploadTestData;
import automation.common.utils.JsonTestDataReader;
import org.testng.annotations.DataProvider;

import java.util.List;

public final class TestDataProviders {

    private TestDataProviders() {
    }

    @DataProvider(name = "fileUploadData")
    public static Object[][] fileUploadData() {
        List<FileUploadTestData> rows = JsonTestDataReader.readList(
                "testdata/file-upload-data.json", FileUploadTestData.class);
        return rows.stream().map(row -> new Object[]{row}).toArray(Object[][]::new);
    }

    @DataProvider(name = "dynamicLoadingData")
    public static Object[][] dynamicLoadingData() {
        List<DynamicLoadingTestData> rows = JsonTestDataReader.readList(
                "testdata/dynamic-loading-data.json", DynamicLoadingTestData.class);
        return rows.stream().map(row -> new Object[]{row}).toArray(Object[][]::new);
    }
}