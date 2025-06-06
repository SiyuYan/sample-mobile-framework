package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PasscodePage extends BasePage {

    public PasscodePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickNewWallet() {
        waitElementVisibleByResourceId("CreateNewWalletButton");
        WebElement createNewWalletButton = findElementByResourceId("CreateNewWalletButton");
        WebElement importWalletButton = findElementByResourceId("ImportWalletButton");
        Assert.assertTrue(createNewWalletButton.isDisplayed(), "Create New Wallet button should be visible");
        Assert.assertTrue(importWalletButton.isDisplayed(), "Import Wallet button should be visible");
        createNewWalletButton.click();
    }

    public void createPasscode(String[] passcodeDigits) {
        String createPasscodeText = "Create passcode";
        String createPasscodeHintText = "Enter your passcode. Be sure to remember it so you can unlock your wallet.";
        waitElementVisibleByText(createPasscodeText);
        WebElement createPasscodeElement = findElementByText(createPasscodeText);
        WebElement createPasscodeHintElement = findElementByText(createPasscodeHintText);
        Assert.assertTrue(createPasscodeElement.isDisplayed(), "Create passcode text should be visible");
        Assert.assertTrue(createPasscodeHintElement.isDisplayed(), "Create passcode hint text should be visible");
        // click digit buttons to create the passcode
        for (String digit : passcodeDigits) {
            WebElement digitButton = findElementByText(digit);
            Assert.assertTrue(digitButton.isDisplayed(), "Digit button " + digit + " should be visible");
            digitButton.click();
        }
    }

    public void confirmPasscode(String[] passcodeDigits) {
        String confirmPasscodeText = "Confirm passcode";
        String confirmPasscodeHintText = "Re-enter your passcode. Be sure to remember it so you can unlock your wallet.";
        waitElementVisibleByText(confirmPasscodeText);
        WebElement confirmPasscodeElement = findElementByText(confirmPasscodeText);
        WebElement confirmPasscodeHintElement = findElementByText(confirmPasscodeHintText);
        Assert.assertTrue(confirmPasscodeElement.isDisplayed(), "Confirm passcode text should be visible");
        Assert.assertTrue(confirmPasscodeHintElement.isDisplayed(), "Confirm passcode hint text should be visible");
        // click digit buttons to confirm the passcode
        for (String digit : passcodeDigits) {
            WebElement digitButton = findElementByText(digit);
            Assert.assertTrue(digitButton.isDisplayed(), "Digit button " + digit + " should be visible");
            digitButton.click();
        }
    }

    public void skipAllSetup() {
        // first skip
        skipSetup("Keep up with the market!");
        try {
            // second skip
            skipSetup("Brilliant, your wallet is ready!");
        } catch (Exception e) {
            System.out.println("No second skip button found, assuming first one was sufficient.");
        }
    }

    public void tryHandleWhatsNewPopupIfExist() {
        String whatsNewText = "What's New";
        try {
            WebElement whatsNewElement = findElementByText(whatsNewText);
            if (whatsNewElement.isDisplayed()) {
                WebElement backButton = findElementByAccessibilityId("Back");
                Assert.assertTrue(backButton.isDisplayed(), "Back button should be visible in 'What's New' popup");
                backButton.click();
            }
        } catch (Exception e) {
            System.out.println("No 'What's New' popup found, continuing with the test.");
        }
    }

    public void verifyPasscodeMismatchErrorExists(String hint) {
        String createPasscodeText = "Create passcode";
        waitElementVisibleByText(createPasscodeText);
        WebElement createPasscodeElement = findElementByText(createPasscodeText);
        Assert.assertTrue(createPasscodeElement.isDisplayed(), "Create passcode text should be visible");
        sleep(2); // Wait for the error message to appear
        WebElement errorMessage = findElementByText(hint);
        Assert.assertTrue(errorMessage.isDisplayed(), hint + " should be visible");
    }
}