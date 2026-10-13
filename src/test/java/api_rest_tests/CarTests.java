package api_rest_tests;

import controllers.CarController;
import dto.Car;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CarFactory;

public class CarTests extends CarController {
    @Test
    public void addNewCarPositiveTest(){
        Car car = CarFactory.positiveCar();
        Assert.assertEquals(addNewCar(car).getStatusCode(), 200);
    }
}
