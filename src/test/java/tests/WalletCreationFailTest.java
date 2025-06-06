package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PasscodePage;
import pages.WalletCreationPage;
import pages.WalletListPage;

@Test(description = "Create 2 wallets with end to end flow")
public class WalletCreationFailTest extends BaseTest {

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
        String[] passcodeDigits = {"1", "2", "3", "4", "5", "6"};
        String[] wrongPasscodeDigits = {"1", "2", "3", "4", "5", "7"};
        passcodePage.clickNewWallet();
        passcodePage.createPasscode(passcodeDigits);
        passcodePage.confirmPasscode(wrongPasscodeDigits);
        passcodePage.verifyPasscodeMismatchErrorExists("Those passwords didn’t match!"); // developer use Chinese’ instead of '
    }

}