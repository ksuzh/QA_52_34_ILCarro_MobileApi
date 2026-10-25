package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;
import screens.RegistrationScreen;

import static utils.UserFactory.positiveUser;

public class RegistrationTests extends TestBase {
    @Test
    public void registrationPositiveTest() {
        new SplashScreen(driver).goToSearchScreen();
        SearchScreen searchScreen = new SearchScreen(driver);
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        User user = positiveUser();
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.clickCheckboxTerms();
        registrationScreen.clickRegYallaBtn();
        searchScreen.clickBtnDots();
        Assert.assertTrue(searchScreen.isBtnLogout());
    }
}
