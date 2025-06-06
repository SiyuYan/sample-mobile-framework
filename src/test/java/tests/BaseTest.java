package tests;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverUtils;

public class BaseTest {

    protected AppiumDriver driver;

    @BeforeClass
    public void setUp() {
        DriverUtils.initializeDriver();
        this.driver = DriverUtils.getDriver();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            DriverUtils.quitDriver();
        }
    }
}
