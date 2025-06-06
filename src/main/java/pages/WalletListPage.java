package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class WalletListPage extends BasePage {

    public WalletListPage(AppiumDriver driver) {
        super(driver);
    }

    public void selectWallet(String walletName) {
        waitElementVisibleByText(walletName);
        WebElement wallet = findElementByText(walletName);
        Assert.assertTrue(wallet.isDisplayed(), walletName + " should be visible");
        wallet.click();
    }

    public void clickAddWallet() {
        waitElementVisibleByResourceId("addWalletIconButton");
        WebElement addWalletButton = findElementByResourceId("addWalletIconButton");
        Assert.assertTrue(addWalletButton.isDisplayed(), "Add Wallet button should be visible");
        addWalletButton.click();
    }

    public void verifyWalletCount(int expectedCount) {
        List<WebElement> walletRows = findElementsByResourceId("walletRow");
        Assert.assertEquals(walletRows.size(), expectedCount, "There should be " + expectedCount + " wallets");
    }

    public void verifyWalletExists(String walletName) {
        WebElement wallet = findElementByText(walletName);
        Assert.assertTrue(wallet.isDisplayed(), walletName + " should be visible");
    }

    public void verifyMultiCoinWalletsText() {
        String multiCoinWalletsText = "Multi-coin wallets";
        waitElementVisibleByText(multiCoinWalletsText);
        WebElement multiCoinWallets = findElementByText(multiCoinWalletsText);
        Assert.assertTrue(multiCoinWallets.isDisplayed(), "Multi-coin wallets text should be visible");
    }
} 