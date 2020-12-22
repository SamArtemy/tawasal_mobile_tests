package Screens;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

public class StartScreen extends BaseScreen{

    @AndroidFindBy(id = "startpage_button_start")
    @iOSXCUITFindBy(xpath = "")
    private SelenideElement buttonStart;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    private SelenideElement inputCodeCountry;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    @iOSXCUITFindBy(xpath = "")
    private SelenideElement inputPhone;

    @AndroidFindBy(xpath = ".//android.widget.TextView")
    @iOSXCUITFindBy(xpath = "")
    private SelenideElement header;

    @Step("Проверка header на экране")
    public void assertIsTextHeader(String expectedText){
        header.shouldHave(Condition.text(expectedText));
    }

    @Step("Проверка input на экране")
    public void assertIsVisibleInputCodeCountry(){
        inputCodeCountry.shouldHave(Condition.visible);
    }

    @Step("Проверка input на экране")
    public void assertIsVisibleInputPhone(){
        inputPhone.shouldHave(Condition.visible);
    }

    @Step("Проверка кнопки на экране")
    public void assertIsVisibleButtonNext(){
        buttonStart.shouldHave(Condition.visible);
    }

    @Step("Тап на кнопку")
    public void clickButtonStart() {
        buttonStart.click();
    }

    @Step("Ввол кода страны")
    public void getInputCodeCountry(String code) {
        inputCodeCountry.sendKeys(code);
    }

    @Step("Ввол номера телефона")
    public void getInputNumber(String phone) {
        inputPhone.sendKeys(phone);
    }

}
