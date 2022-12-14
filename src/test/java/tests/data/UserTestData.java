package tests.data;

import com.github.javafaker.Faker;
import tests.data.RandomUtils;

import java.util.HashMap;

public class UserTestData {
    RandomUtils randomUtils = new RandomUtils();

    //CREATE USER
    Faker faker = new Faker();
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = firstName + lastName + "@test.com";
    public String password = randomUtils.getRandomString(16);
    public String mobileNumber = randomUtils.getRandomLong(00000000L, 99999999L).toString();
}
