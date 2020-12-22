package Base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.ScreenOrientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseTest {

    private AppiumDriver driver;
    public Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        setDriverConfig();
            driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        WebDriverRunner.setWebDriver(driver);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            WebDriverRunner.closeWebDriver();
            driver.quit();
        }
        SelenideLogger.removeListener("AllureSelenide");
    }

    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }

    private void setDriverConfig() {
        Configuration.screenshots = false;
        Configuration.timeout = 8000;
    }

    @AfterMethod
    protected void resetApp() throws MalformedURLException {
        if (driver == null) {
            setUp();
        }
        driver.resetApp();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.of(seconds, SECONDS));
    }


}
