package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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

    protected WebElement selectClickableByResourceId(String resourceId) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")"));
    }

    protected void waitClickableByResourceId(String resourceId) {
        driverWait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")")));
    }

    protected WebElement selectClickableViewByText(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    protected void waitForTextClickable(String text) {
        driverWait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")")));
    }

    protected WebElement selectClickableByAccessibilityId(String accessibilityId) {
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
        waitForTextClickable(skipText);
        WebElement skipButton = selectClickableViewByText(skipText);
        Assert.assertTrue(skipButton.isDisplayed(), errorMessage);
        skipButton.click();
    }
}
