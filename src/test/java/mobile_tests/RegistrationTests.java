package mobile_tests;

import data_providers.UserDataProvider;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import screens.RegistrationScreen;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {
    SearchScreen searchScreen;
    RegistrationScreen registrationScreen;
//    ErrorScreen errorScreen;
    User user;


    @BeforeMethod
    public void goToRegistrationScreen() {
        new SplashScreen(driver).goToSearchScreen();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        registrationScreen = new RegistrationScreen(driver);
        user = positiveUser();
    }

    @Test
    public void registrationPositiveTest() {
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.clickCheckboxTerms();
        registrationScreen.clickRegYallaBtn();
        Assert.assertTrue(searchScreen
                .validatePopUpRegistrationSuccess("Registration success!"));
    }


    @Test
    public void registrationEmptyFieldNameNegativeTest() {
        user.setFirstName("");
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.clickCheckboxTerms();
        registrationScreen.clickRegYallaBtn();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("All fields must be filled and agree terms"));
    }

    @Test(dataProvider = "dataProviderWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void registrationWrongPasswordOrEmailNegativeTest(User user) {
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.clickCheckboxTerms();
        registrationScreen.clickRegYallaBtn();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("{password= At least 8 characters; " +
                        "Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; " +
                        "Can contain special characters [@$#^&*!]}"));

    }

    @Test
    public void duplicateEmailNegativeTest() {
        user.setUsername(getProperty("base.properties", "email"));
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.clickCheckboxTerms();
        registrationScreen.clickRegYallaBtn();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("User already exists"));
    }
}
