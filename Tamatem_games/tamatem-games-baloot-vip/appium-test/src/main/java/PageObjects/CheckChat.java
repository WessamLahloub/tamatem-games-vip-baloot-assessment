package PageObjects;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.android.AndroidDriver;
import Locators.CheckChatLocators;
import org.testng.Assert;

public class CheckChat {
    private static final Logger logger = LoggerFactory.getLogger(CheckChat.class); // Logger instance
    private final AndroidDriver driver; // Driver instance
    private final WebDriverWait wait; // WebDriverWait instance

    // Constructor to initialize driver and wait instance
    public CheckChat(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    /**
     * Start the game, skip the tutorial, and open the chat feature
     */
    public void TestChatFunctionality() throws InterruptedException {

        // Wait for 10 seconds to ensure the app is fully loaded ( and avoid possible
        // flakiness)
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        // click enter as a guest button if it's visible
        try {
            WebElement guestBtn = driver.findElement(CheckChatLocators.ENTER_AS_GUEST_BUTTON);
            guestBtn.click();
            System.out.println("Clicked 'Enter as a Guest' button successfully.");
        } catch (Exception e) {
            System.out.println("'Enter as a Guest' button was not found or not clickable.");
            e.printStackTrace();
        }

        // Skip the tutorial by clicking the skip button if it's visible
        try {
            WebElement skipButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            CheckChatLocators.SKIP_BUTTON));
            skipButton.click();
            logger.info("Tutorial skipped successfully.");
        } catch (TimeoutException e) {
            logger.warn("Skip button not found. It might have been skipped already.");
        }

        // Assert that the main game page opened and "play now" text is visible
        // locate the element
        WebElement playNowButton = driver.findElement(CheckChatLocators.ASSERT_PLAY_NOW_VISIBILITY);

        // get its text
        String actualText = playNowButton.getText();

        // assert it equals "play now"
        Assert.assertEquals(actualText.trim().toLowerCase(), "play now",
                "Expected text 'play now' but found: " + actualText);

        // Open the chat feature by clicking the chat icon/button
        driver.findElement(CheckChatLocators.CHAT_BUTTON).click();

        /*
         * Verify that the chat interface is displayed, *** the following is commented
         * on purpose ***
         * as the chat page is invisible to appium inspector because of webview or
         * custom rendering elements
         */

        // Attempt assert new topic text is visible using locator (normal way)
        // WebElement newTopicBar = driver.findElement(CheckChatLocators.NEW_TOPIC_BAR);
        // Assert.assertTrue(newTopicBar.isDisplayed(), "Chat interface is not
        // displayed.");
        // logger.info("Chat page is displayed successfully.");
    }

}
