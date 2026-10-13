package api_rest_tests;

import controllers.AuthController;
import data_providers.UserDataProvider;
import dto.TokenDto;
import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesReader;

import static io.restassured.RestAssured.given;
import static utils.UserFactory.*;



public class RegistrationLoginTests extends AuthController {
    @Test
    public void registrationPositiveTest(){
        User user = positiveUser();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(),200);
    }

    @Test(dataProvider = "dataProviderWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void wrongDataRegistrationNegativeTest(User user){
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(),400);
    }

    @Test
    public void duplicateUserRegistrationNegativeTest(){
        User user = positiveUser();
        registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(),400);
    }

    @Test
    public void emptyEmailRegistrationNegativeTest(){
        User user = positiveUser();
        user.setUsername("");
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(),400);
    }

    @Test
    public void emptyNameRegistrationNegativeTest(){
        User user = positiveUser();
        user.setFirstName("");
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(),400);
    }


    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        System.out.println(response.getBody().asString());
        TokenDto tokenDto = response.getBody().as(TokenDto.class);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void wrongPasswordLoginNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "wrongPassword"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(),401);
    }

    @Test
    public void wrongEmailLoginNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "wrongEmail"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(),401);
    }


    @Test
    public void negativeContentTypeTest() {
        Response response = given()
                .contentType(ContentType.TEXT)
                .body("""
                        {
                            "username": "w1@gmail.com"
                            "password: "Qwerty!123"
                        """)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn();
        Assert.assertEquals(response.statusCode(), 500);
    }
}
