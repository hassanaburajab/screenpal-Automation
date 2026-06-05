package main;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;

    protected String validEmail    = "hassanaburajab87@gmail.com";
    protected String validPassword = "MoMo123159!@";

    private final By profileButton = By.xpath("//button[@x-ref='profileButton']");
    private final By logoutButton  = By.xpath("//a[@href='https://screenpal.com/logout']");

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        loginPage = new LoginPage(driver, wait);
    }

    protected void login() {
        loginPage.openLoginPage();
        loginPage.enterEmail(validEmail);
        loginPage.clickContinue();
        loginPage.enterPassword(validPassword);
        loginPage.clickLogin();
    }

    protected void openProfileMenu() {
        WebElement btn = wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(profileButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    protected void logout() {
        openProfileMenu();
        wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(logoutButton)
        ).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}