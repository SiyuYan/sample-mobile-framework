package tests;

import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PasscodePage;
import pages.WalletListPage;
import pages.WalletCreationPage;
import utils.TestUtils;

@Tag("create-wallet")
@Test(description = "Create 2 wallets with end to end flow")
public class WalletCreationTest extends BaseTest {

    private PasscodePage passcodePage;
    private WalletListPage walletListPage;
    private WalletCreationPage walletCreationPage;

    @BeforeClass
    public void setUp() {
        super.setUp();
        passcodePage = new PasscodePage(this.driver);
        walletListPage = new WalletListPage(this.driver);
        walletCreationPage = new WalletCreationPage(this.driver);
    }

    @Test(description = "Create first wallet success with passcode")
    public void testCreateFirstWallet() {
        String[] passcodeDigits = TestUtils.generateRandomPasscode();
        passcodePage.clickNewWallet();
        passcodePage.createPasscode(passcodeDigits);
        passcodePage.confirmPasscode(passcodeDigits);
        passcodePage.skipAllSetup();
        passcodePage.tryHandleWhatsNewPopupIfExist();
    }

    @Test(description = "Navigate to wallet creation screen after creation", dependsOnMethods = "testCreateFirstWallet")
    public void testNavigateToWalletCreation() {
        walletListPage.selectWallet("Main Wallet 1");
        walletListPage.clickAddWallet();
    }

    @Test(description = "Create second wallet", dependsOnMethods = "testNavigateToWalletCreation")
    public void testCreateSecondWallet() {
        walletCreationPage.clickCreateNewWallet();
        walletCreationPage.clickCreateSecretPhrase();
        walletCreationPage.skipSetup("Brilliant, your wallet is ready!");
    }

    @Test(description = "Verify wallet creation results", dependsOnMethods = "testCreateSecondWallet")
    public void testVerifyWalletCreation() {
        walletListPage.selectWallet("Main Wallet 2");
        walletListPage.verifyMultiCoinWalletsText();
        walletListPage.verifyWalletCount(2);
        walletListPage.verifyWalletExists("Main Wallet 1");
        walletListPage.verifyWalletExists("Main Wallet 2");
    }
}