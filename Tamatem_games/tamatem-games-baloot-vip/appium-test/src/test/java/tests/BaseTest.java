package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.CheckChat;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static AndroidDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        logger.info("Setting up the Appium Driver...");

        // Set UiAutomator2 Options with capabilities (mandatory due to appium2 update)
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("platformName", "Android");
        options.setCapability("deviceName", "emulator-5554");
        options.setCapability("appPackage", "co.tamatem.vipbaloot");
        options.setCapability("appActivity", "com.casualino.base.activities.UIActivity");
        options.setCapability("automationName", "UiAutomator2");
        options.setCapability("noReset", true);
        options.setCapability("fullReset", false);
        options.setCapability("allowCors", true);
        options.setCapability("newCommandTimeout", 60); // Optional: set timeout in seconds

        // Initialize AndroidDriver with the URL for Appium server
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        logger.info("Appium Driver setup is complete.");
    }

    /**
        Test following flow -> enter the game as a guest, skip the tutorial -> open the chat
        feature 
     */
    @Test(description = "check chat feature is working fine")
    public void testChatFeature() throws InterruptedException {
        logger.info("Executing test: Test chat feature functionality");
        CheckChat chatFeature = new CheckChat(driver);
        chatFeature.TestChatFunctionality();
    }
    

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver has been closed.");
        }
    }
}
