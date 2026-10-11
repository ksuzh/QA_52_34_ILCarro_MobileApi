package utils;

import dto.User;
import net.datafaker.Faker;
import static utils.PropertiesReader.*;

public class PositiveUserFactory {
    static Faker faker = new Faker();

    public static User positiveUser() {
        User user = User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(getProperty("base.properties", "password"))
                .build();
        return user;
    }
}
