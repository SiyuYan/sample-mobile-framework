package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WalletCreationPage extends BasePage {
    
    public WalletCreationPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickCreateNewWallet() {
        waitElementVisibleByResourceId("CreateNewWallet");
        WebElement createNewWallet = findElementByResourceId("CreateNewWallet");
        Assert.assertTrue(createNewWallet.isDisplayed(), "Create New Wallet option should be visible");
        createNewWallet.click();
    }

    public void clickCreateSecretPhrase() {
        waitElementVisibleByResourceId("secretPhraseCreateButton");
        WebElement secretPhraseButton = findElementByResourceId("secretPhraseCreateButton");
        Assert.assertTrue(secretPhraseButton.isDisplayed(), "Create Secret Phrase button should be visible");
        secretPhraseButton.click();
    }
} 