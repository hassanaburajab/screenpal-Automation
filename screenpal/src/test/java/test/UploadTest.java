package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.BaseTest;
import main.UploadPage;

public class UploadTest extends BaseTest {

    private UploadPage uploadPage;

    @Override
    @BeforeClass
    public void setup() {
        super.setup();                           
        uploadPage = new UploadPage(driver, wait);
    }

    @Test(priority = 1)
    public void uploadValidVideo() {
        login();
        uploadPage.clickUpload();
        uploadPage.uploadFile("C:\\Users\\hassan aburajab\\Desktop\\11.mp4");
        uploadPage.waitForSuccessAndNavigate();
    }

    @Test(priority = 2)
    public void uploadInvalidFile() {
        uploadPage.clickUpload();
        uploadPage.uploadFile("C:\\Users\\hassan aburajab\\Desktop\\11.txt");

        String error = uploadPage.getErrorMessage();
        System.out.println("ERROR: " + error);
        Assert.assertFalse(error.isEmpty());
    }

  
}