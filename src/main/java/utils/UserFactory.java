package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveUser() {
        User user = User.builder()
                .username(faker.internet().emailAddress())
                .password("Qwerty123!")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
        return user;
    }
}
