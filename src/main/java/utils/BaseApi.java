package utils;

public interface BaseApi {
    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REGISTRATION_URL = "/v1/user/registration/usernamepassword";
    String LOGIN_URL = "/v1/user/login/usernamepassword";

    String AUTH = "Authorization";
    String ADD_CAR_URL = "/v1/cars";
    String GET_USER_CARS_URL = "/v1/cars/my";
    String SEARCH_CAR_URL = "/v1/cars/search";
    String BOOK_CAR_URL = "/v1/cars/{serialNumber}/booking";
    String DELETE_CAR_URL = "/v1/cars/{serialNumber}";
}
