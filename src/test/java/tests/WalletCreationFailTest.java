package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PasscodePage;

@Test(description = "Create wallet failure test")
public class WalletCreationFailTest extends BaseTest {

    private PasscodePage passcodePage;

    @BeforeClass
    public void setUp() {
        super.setUp();
        passcodePage = new PasscodePage(this.driver);
    }

    @Test(description = "Confirmation passcode is not match with passcode")
    public void testCreateFirstWallet() {
        String[] passcodeDigits = {"1", "2", "3", "4", "5", "6"};
        String[] wrongPasscodeDigits = {"1", "2", "3", "4", "5", "7"};
        passcodePage.clickNewWallet();
        passcodePage.createPasscode(passcodeDigits);
        passcodePage.confirmPasscode(wrongPasscodeDigits);
        passcodePage.verifyPasscodeMismatchErrorExists("Those passwords didn’t match!"); // developer use Chinese’ instead of '
    }
}