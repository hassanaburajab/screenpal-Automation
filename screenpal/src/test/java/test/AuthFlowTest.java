package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import main.BaseTest;

public class AuthFlowTest extends BaseTest {

    @Test(priority = 1)
    public void loginWithValidInput() {
        login();
        System.out.println("Login successful");
    }

    @Test(priority = 2, dependsOnMethods = "loginWithValidInput")
    public void logoutTest() {
        logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("screenpal"));
        System.out.println("Logout successful");
    }

    @Test(priority = 3)
    public void loginWithInvalidPassword() {
        loginPage.openLoginPage();
        loginPage.enterEmail(validEmail);
        loginPage.clickContinue();
        loginPage.enterPassword("WrongPassword123!q@");
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        System.out.println("Error: " + error);
        Assert.assertFalse(error.isEmpty());
    }
}