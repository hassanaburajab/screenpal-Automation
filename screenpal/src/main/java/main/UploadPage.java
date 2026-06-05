package main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadPage {

    WebDriver driver;
    WebDriverWait wait;

    public UploadPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By uploadContent = By.xpath("//*[contains(text(),'Upload Content')]");
    By upload = By.xpath("//*[@id=\"drop-area\"]/form/div[3]/label");
    By successMessage = By.xpath("//*[contains(text(),'uploaded')]");
    public void clickUpload() {

        By uploadContent = By.xpath("//*[contains(text(),'Upload Content')]");

        WebElement el = wait.until( ExpectedConditions.presenceOfElementLocated(uploadContent));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }
    public void uploadFile(String filePath) {
        WebElement input = driver.findElement(By.xpath("//input[@type='file']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", input);
        input.sendKeys(filePath);
    }

    public void waitForSuccessAndNavigate() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='sidebar-folders-list-item']"))).click();

        List<WebElement> videos = driver.findElements(By.xpath("//*[contains(text(),'11')]"));

        if (!videos.isEmpty()) {
            System.out.println("Video 11 added successfully");
        } else {
            System.out.println("Video 11 not found");
        }
    }

    public String getErrorMessage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,\"file type isn't supported\")]"))).getText();
    }
 
}