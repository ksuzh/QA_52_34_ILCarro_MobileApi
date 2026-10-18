package api_rest_tests;

import controllers.CarController;
import dto.BookingPeriodDto;
import dto.Car;
import dto.ResponseMessageDto;
import dto.SearchDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CarFactory;

import java.time.LocalDate;
import java.util.List;

public class CarTests extends CarController {
    List<Car> cars;

    @Test
    public void addNewCarPositiveTest(){
        Car car = CarFactory.positiveCar();
        Assert.assertEquals(addNewCar(car).getStatusCode(), 200);
    }

    @Test
    public void getUserCarsPositiveTest(){
        Response response = getCars();
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void searchCarPositiveTest(){
        SearchDto searchDto = SearchDto.builder()
                .city("Tel Aviv")
                .startDate(LocalDate.now().plusDays(1).toString())
                .endDate(LocalDate.now().plusDays(4).toString())
                .build();
        Response response = searchCars(searchDto);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void bookCarPositiveTest(){
        BookingPeriodDto bookingPeriod = BookingPeriodDto.builder()
                .startDate(LocalDate.now().plusDays(1).toString())
                .endDate(LocalDate.now().plusDays(4).toString())
                .build();
//        List<Car> cars = getCars().jsonPath().getList("cars", Car.class);
//        String serialNumber = cars.get(0).getSerialNumber();
//        Response response = bookCar(serialNumber, bookingPeriod);
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Response carResponse = getCars();
//        System.out.println(carResponse.asString());
//        System.out.println("=============");
        cars = getCarList();
        String serialNumber = cars.get(0).getSerialNumber();
        System.out.println(serialNumber);
        Assert.assertEquals(bookCar(serialNumber, bookingPeriod).getStatusCode(), 200);
    }

    @Test
    public void deleteCarPositiveTest(){
        cars = getCarList();
        String serialNumber = cars.get(1).getSerialNumber();
        System.out.println(serialNumber);
        Assert.assertEquals(deleteCar(serialNumber).getStatusCode(), 200);
    }

}
