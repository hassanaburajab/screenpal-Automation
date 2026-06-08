package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "invalidPasswords")
    public Object[][] invalidPasswordsData() {
        return new Object[][] {
            { "short1!"           },                
            { "NoSpecialChar123"  },   
            { "wrongpassword123!" },   
            { "WrongPassword123!q@"}
        };
    }

    @Test(priority = 3, dataProvider = "invalidPasswords")
    public void loginWithInvalidPassword(String password) {
        loginPage.openLoginPage();
        loginPage.enterEmail(validEmail);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        System.out.println("Testing password: [" + password + "] → Error: " + error);
        Assert.assertFalse(error.isEmpty(),
            "Expected error message for password: " + password);
    }
}