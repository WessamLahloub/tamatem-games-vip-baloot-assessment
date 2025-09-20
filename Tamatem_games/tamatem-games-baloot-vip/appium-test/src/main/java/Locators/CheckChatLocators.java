package Locators;

import org.openqa.selenium.By;

/**
 * scalable, reusable locators for CheckChat page feature.
 * Add new locators here to keep page objects clean and maintainable.
 */
public final class CheckChatLocators {
    private CheckChatLocators() { /* utility class */ }


    public static final By ENTER_AS_GUEST_BUTTON = By.xpath("//android.widget.Button[@text='Enter as a Guest']");
    public static final By SKIP_BUTTON = By.xpath("//android.widget.Button[@text='Skip']");
    public static final By ASSERT_PLAY_NOW_VISIBILITY = By.xpath("//android.widget.Button[@text=\"Play now\"]");
    public static final By CHAT_BUTTON = By.xpath("//android.widget.Button[@text='Chat']");
    
    // Unused locator for custom rendering issue
    public static final By NEW_TOPIC_BAR = By.xpath("//*[contains(@text, 'New topic')]");

}
