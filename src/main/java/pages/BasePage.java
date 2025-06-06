package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait driverWait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected WebElement findElementByResourceId(String resourceId) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")"));
    }
    protected List<WebElement> findElementsByResourceId(String resourceId) {
        return driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")"));
    }

    protected void waitElementVisibleByResourceId(String resourceId) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")")));
    }

    protected WebElement findElementByText(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    protected void waitElementVisibleByText(String text) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")")));
    }

    protected WebElement findElementByAccessibilityId(String accessibilityId) {
        return driver.findElement(AppiumBy.accessibilityId(accessibilityId));
    }

    public void skipSetup() {
        skipSetup(null);
    }

    public void skipSetup(String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = "Skip button should be visible";
        }
        String skipText = "Skip, I'll do it later";
        waitElementVisibleByText(skipText);
        WebElement skipButton = findElementByText(skipText);
        Assert.assertTrue(skipButton.isDisplayed(), errorMessage);
        skipButton.click();
    }
}
