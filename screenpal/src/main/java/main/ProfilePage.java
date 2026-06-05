package main;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    WebDriver driver;
    WebDriverWait wait;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    
    By profileButton = By.xpath("//button[@x-ref='profileButton']");
    By sitting = By.xpath("//a[@href='https://screenpal.com/settings/profile' and @class='dropdown-item']");

    By profileLink = By.xpath("//li[@class='sidebar-item nav-item nav-link cursor-pointer mb-1 current']");

    By firstname = By.xpath("//input[@id='firstname']");
    By saveButton = By.xpath("//button[@type='submit']");

    By successMessage = By.xpath("/html/body/div[1]/div[2]/div[1]");

    public void openProfilePage() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sitting)).click();

        wait.until(ExpectedConditions.elementToBeClickable(profileLink)).click();
    }

    public void updateDisplayName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstname)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstname)).sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}