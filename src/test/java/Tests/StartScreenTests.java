package Tests;

import Base.BaseTest;
import Screens.StartScreen;
import org.testng.annotations.Test;

import static appium.ScreenObject.screen;


public class StartScreenTests extends BaseTest {

    @Test
    public void openScreenStart() {
        StartScreen startScreen = screen(StartScreen.class);

        startScreen.clickButtonStart();
        startScreen.assertIsTextHeader("Enter your phone number");
        startScreen.assertIsVisibleButtonNext();
        startScreen.assertIsVisibleInputPhone();
        startScreen.assertIsVisibleInputCodeCountry();
    }
}
