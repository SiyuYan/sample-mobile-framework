package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PasscodePage extends BasePage {

    public PasscodePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickNewWallet() {
        waitClickableByResourceId("CreateNewWalletButton");
        WebElement createNewWalletButton = selectClickableByResourceId("CreateNewWalletButton");
        Assert.assertTrue(createNewWalletButton.isDisplayed(), "Create New Wallet button should be visible");
        createNewWalletButton.click();
    }

    public void createPasscode(String[] passcodeDigits) {
        String createPasscodeText = "Create passcode";
        waitForTextClickable(createPasscodeText);
        WebElement createPasscodeElement = selectClickableViewByText(createPasscodeText);
        Assert.assertTrue(createPasscodeElement.isDisplayed(), "Create passcode text should be visible");
        // click digit buttons to create the passcode
        for (String digit : passcodeDigits) {
            WebElement digitButton = selectClickableViewByText(digit);
            Assert.assertTrue(digitButton.isDisplayed(), "Digit button " + digit + " should be visible");
            digitButton.click();
        }
    }

    public void confirmPasscode(String[] passcodeDigits) {
        String confirmPasscodeText = "Confirm passcode";
        waitForTextClickable(confirmPasscodeText);
        WebElement confirmPasscodeElement = selectClickableViewByText(confirmPasscodeText);
        Assert.assertTrue(confirmPasscodeElement.isDisplayed(), "Confirm passcode text should be visible");
        // click digit buttons to confirm the passcode
        for (String digit : passcodeDigits) {
            WebElement digitButton = selectClickableViewByText(digit);
            Assert.assertTrue(digitButton.isDisplayed(), "Digit button " + digit + " should be visible");
            digitButton.click();
        }
    }

    public void skipAllSetup() {
        // first skip
        skipSetup( "First Skip button should be visible");
        try {
            // second skip
            skipSetup("Second Skip button should be visible");
            sleep(2);
        } catch (Exception e) {
            // If the second skip button is not found, we can assume the first one was sufficient
            sleep(2);
        }
    }

    public void tryHandleWhatsNewPopupIfExist() {
        String whatsNewText = "What's New";
        try {
            WebElement whatsNewElement = selectClickableViewByText(whatsNewText);
            if (whatsNewElement.isDisplayed()) {
                WebElement backButton = selectClickableByAccessibilityId("Back");
                Assert.assertTrue(backButton.isDisplayed(), "Back button should be visible in 'What's New' popup");
                backButton.click();
            }
        } catch (Exception e) {
            System.out.println("No 'What's New' popup found, continuing with the test.");
        }
    }

    public void verifyPasscodeMismatchErrorExists(String hint) {
        String createPasscodeText = "Create passcode";
        waitForTextClickable(createPasscodeText);
        WebElement createPasscodeElement = selectClickableViewByText(createPasscodeText);
        Assert.assertTrue(createPasscodeElement.isDisplayed(), "Create passcode text should be visible");
        sleep(2); // Wait for the error message to appear
        WebElement errorMessage = selectClickableViewByText(hint);
        Assert.assertTrue(errorMessage.isDisplayed(), hint + " should be visible");
    }
}