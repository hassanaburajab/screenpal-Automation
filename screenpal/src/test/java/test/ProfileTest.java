package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.BaseTest;
import main.ProfilePage;

public class ProfileTest extends BaseTest {

    private ProfilePage profilePage;

    @Override
    @BeforeClass
    public void setup() {
        super.setup();
        profilePage = new ProfilePage(driver, wait);
    }

    @Test(priority = 1)
    public void openProfilePage() {
        login();
        profilePage.openProfilePage();
        System.out.println("Profile page opened successfully");
    }

    @Test(priority = 2)
    public void updateDisplayName() {
        profilePage.openProfilePage();
        profilePage.updateDisplayName("hazem");

        String msg = profilePage.getSuccessMessage();
        Assert.assertTrue(msg.toLowerCase().contains("update"));
        System.out.println("Profile updated successfully");
    }
}