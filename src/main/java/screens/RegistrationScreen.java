package screens;

import dto.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationScreen extends BaseScreen{
    public RegistrationScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.telran.ilcarro:id/editRegName']")
    WebElement firstName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.telran.ilcarro:id/editRegLastName']")
    WebElement lastName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.telran.ilcarro:id/editRegEmail']")
    WebElement username;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.telran.ilcarro:id/editRegPassword']")
    WebElement password;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@resource-id='com.telran.ilcarro:id/checkBoxAgree']")
    WebElement checkboxTerms;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.telran.ilcarro:id/regBtn']")
    WebElement regYallaBtn;

    public void fillRegistrationForm(User user) {
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());
    }

    public void clickCheckboxTerms() {
        checkboxTerms.click();
    }

    public void clickRegYallaBtn() {
        regYallaBtn.click();
    }
}
