package tests.user;

import com.github.javafaker.Faker;
import data.RandomUtils;

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

    public HashMap getAddToCartParams() {
    HashMap<String, String> addToCartParams = new HashMap<>();
    addToCartParams.put("product", "4404");
    addToCartParams.put("item", "4404");
    addToCartParams.put("qty", "1");
    addToCartParams.put("options[60382]", "{\"height\":120,\"width\":190,\"width1\":95,\"fieldwidth2\":95}");
        return addToCartParams;
    }
}
