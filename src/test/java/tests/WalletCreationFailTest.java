package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PasscodePage;
import utils.TestUtils;

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
        String[] passcodeDigits = TestUtils.generateRandomPasscode();
        String[] wrongPasscodeDigits = TestUtils.generateRandomPasscode();
        passcodePage.clickNewWallet();
        passcodePage.createPasscode(passcodeDigits);
        passcodePage.confirmPasscode(wrongPasscodeDigits);
        passcodePage.verifyPasscodeMismatchErrorExists("Those passwords didn’t match!"); // developer use Chinese’ instead of '
    }
}