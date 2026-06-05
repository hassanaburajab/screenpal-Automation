package main;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By emailField = By.id("email");
    By continueButton = By.xpath("//button[@data-callback='onSubmitlogin']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//button[@data-callback='onSubmitpassword']");
    By errorMessage = By.xpath("//*[@id='password-error']/strong");

    public void openLoginPage() {
        driver.get("https://screenpal.com/login");
    }

    public void enterEmail(String email) {
        WebElement emailEl = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailEl.clear();
        emailEl.sendKeys(email);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void enterPassword(String password) {
        WebElement passEl = wait.until( ExpectedConditions.visibilityOfElementLocated(passwordField));
        passEl.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }
}