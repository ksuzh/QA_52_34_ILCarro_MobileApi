package controllers;

import dto.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utils.BaseApi;
import utils.PropertiesReader;

import java.util.List;

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

    public Response getCars(){
        return given()
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .get(BASE_URL + GET_USER_CARS_URL)
                .thenReturn();
    }

    public Response searchCars(SearchDto searchDto){
        return given()
                .body(searchDto)
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .post(BASE_URL + SEARCH_CAR_URL)
                .thenReturn();
    }

    public Response bookCar(String serialNumber, BookingPeriodDto bookingPeriodDto){
        return given()
                .body(bookingPeriodDto)
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .pathParam("serialNumber", serialNumber)
                .when()
                .post(BASE_URL + BOOK_CAR_URL)
                .thenReturn();
    }

    public Response deleteCar(String serialNumber){
        return given()
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .pathParam("serialNumber", serialNumber)
                .when()
                .delete(BASE_URL + DELETE_CAR_URL)
                .thenReturn();
    }

    public List<Car> getCarList(){
        return getCars().as(CarsDto.class).getCars();
    }

}
