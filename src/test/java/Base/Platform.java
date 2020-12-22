package Base;

import Utils.PlatformConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;


public class Platform {

    public PlatformConfig cfg = ConfigFactory.create(PlatformConfig.class, System.getProperties());
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final Platform instance = new Platform();
    public final String platformName;


    public static Platform getInstance() {
        return instance;
    }

    private Platform() {
        platformName = cfg.platformName();
    }

    AppiumDriver getDriver() throws MalformedURLException {
        URL URL = new URL("http://127.0.0.1:4723/wd/hub/");
        if (isAndroid()) {
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        } else if (isIOS()) {
            return new IOSDriver(URL, getIOSDesiredCapabilities());
        } else {
            throw new IllegalArgumentException("Cannot detect type of the Driver. Platform value: " + platformName);
        }
    }

    public boolean isAndroid() {
        return PLATFORM_ANDROID.equals(platformName);
    }

    public boolean isIOS() {
        return PLATFORM_IOS.equals(platformName);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", resourcePath("apk/debug_2.0.26.7008.apk"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, cfg.deviceName());
        capabilities.setCapability("udid", cfg.udid());
        capabilities.setCapability("avd", cfg.avd());
        capabilities.setCapability("appWaitPackage", cfg.appPackage());
        capabilities.setCapability("appWaitActivity", cfg.appActivity() + ".SplashActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability("sendKeyStrategy", "setValue");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "8");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability("platformVersion", cfg.platformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, cfg.deviceName());
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("app", resourcePath("app/Messenger.iOS.ipa"));
        capabilities.setCapability("noReset", true);
        return capabilities;
    }

    private String resourcePath(String filePath) {
        String resource = Paths.get("src", "test", "resources", filePath).toFile().getAbsolutePath();
        Assert.assertNotNull(resource, "Resource path");
        return resource;
    }
}
