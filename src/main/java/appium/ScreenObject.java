package appium;

import com.codeborne.selenide.impl.SelenidePageFactory;

import java.lang.reflect.Constructor;

import static com.codeborne.selenide.WebDriverRunner.driver;

public class ScreenObject {
  /**
   * Create a Page Object instance.
   */
  public static <PageObjectClass> PageObjectClass screen(Class<PageObjectClass> pageObjectClass) {
    return screen(createInstance(pageObjectClass));
  }

  /**
   * Initialize a Page Object fields annotated with @FindBy, @AndroidFindBy, @iOSFindBy etc.
   */
  public static <PageObjectClass, T extends PageObjectClass> PageObjectClass screen(T pageObject) {
    new SelenidePageFactory().initElements(new SelenideAppiumFieldDecorator(driver()), pageObject);
    return pageObject;
  }

  private static <PageObjectClass> PageObjectClass createInstance(Class<PageObjectClass> pageObjectClass) {
    try {
      Constructor<PageObjectClass> constructor = pageObjectClass.getDeclaredConstructor();
      constructor.setAccessible(true);
      return constructor.newInstance();
    }
    catch (Exception e) {
      throw new RuntimeException("Failed to create new instance of " + pageObjectClass, e);
    }
  }
}
