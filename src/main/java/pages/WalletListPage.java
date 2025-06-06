package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class WalletListPage extends BasePage {
    
    public WalletListPage(AppiumDriver driver) {
        super(driver);
    }

    public void selectWallet(String walletName) {
        waitForTextClickable(walletName);
        WebElement wallet = selectClickableViewByText(walletName);
        Assert.assertTrue(wallet.isDisplayed(), walletName + " should be visible");
        wallet.click();
    }

    public void clickAddWallet() {
        waitClickableByResourceId("addWalletIconButton");
        WebElement addWalletButton = selectClickableByResourceId("addWalletIconButton");
        Assert.assertTrue(addWalletButton.isDisplayed(), "Add Wallet button should be visible");
        addWalletButton.click();
    }

    public void verifyWalletCount(int expectedCount) {
        System.out.println("Verifying wallet count. Expected: " + expectedCount);
        List<WebElement> walletRows = driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"walletRow\")"));
        Assert.assertEquals(walletRows.size(), expectedCount, "There should be " + expectedCount + " wallets");
        System.out.println("Wallet count verified: " + walletRows.size());
    }

    public void verifyWalletExists(String walletName) {
        System.out.println("Verifying wallet exists: " + walletName);
        WebElement wallet = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"textCellTitle\").text(\"" + walletName + "\")"));
        Assert.assertTrue(wallet.isDisplayed(), walletName + " should be visible");
        System.out.println("Wallet verified: " + walletName);
    }

    public void verifyMultiCoinWalletsText() {
        System.out.println("Verifying Multi-coin wallets text");
        String multiCoinWalletsText = "Multi-coin wallets";
        waitForTextClickable(multiCoinWalletsText);
        WebElement multiCoinWallets = selectClickableViewByText(multiCoinWalletsText);
        Assert.assertTrue(multiCoinWallets.isDisplayed(), "Multi-coin wallets text should be visible");
        System.out.println("Multi-coin wallets text verified");
    }
} 