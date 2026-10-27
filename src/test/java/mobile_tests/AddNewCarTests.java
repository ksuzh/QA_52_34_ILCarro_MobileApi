package mobile_tests;

import dto.Car;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.CarFactory;

import static utils.PropertiesReader.getProperty;

public class AddNewCarTests extends TestBase {
    AddNewCarScreen addNewCarScreen;
    MyCarsScreen myCarsScreen;
    Car car;

    @BeforeMethod
    public void goToLoginScreen() {
        new SplashScreen(driver).goToSearchScreen();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
        LoginScreen loginScreen = new LoginScreen(driver);
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYallaLogin();
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        addNewCarScreen = new AddNewCarScreen(driver);
        car = CarFactory.positiveCar();
    }

    @Test
    public void AddNewCarPositiveTest() {
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(myCarsScreen.validatePopUpCarWasAdded("Car was added!"));
    }
}
