package controllers;

import dto.Car;
import dto.TokenDto;
import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utils.BaseApi;
import utils.PropertiesReader;

import static io.restassured.RestAssured.given;

public class CarController implements BaseApi {

    public TokenDto tokenDto;
    @BeforeClass
    public void login(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        tokenDto = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_URL)
                .thenReturn()
                .getBody()
                .as(TokenDto.class);
    }

    public Response addNewCar(Car car){
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .post(BASE_URL + ADD_CAR_URL)
                .thenReturn();
    }

}
