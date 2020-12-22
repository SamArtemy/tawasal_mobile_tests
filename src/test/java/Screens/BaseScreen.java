package Screens;

import Base.Platform;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriverException;

public class BaseScreen {

    private AppiumDriver driver = (AppiumDriver) WebDriverRunner.getWebDriver();

    protected String whichPlatform() {
        return Platform.getInstance().platformName;
    }

    protected void sendKeys(String text) {
        int maxAttempt = 5;
        for (int attempt = 1; attempt <= maxAttempt; attempt++) {
            try {
                driver.getKeyboard().sendKeys(text);
                return;
            } catch (WebDriverException e) {
                if (attempt >= maxAttempt) {
                    throw new AssertionError(String.format("Tried %s times to do getKeyboard() but failed", maxAttempt));
                }
            }
        }
    }

}
