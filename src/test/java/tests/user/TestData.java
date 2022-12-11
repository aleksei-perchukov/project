package tests.user;

import com.github.javafaker.Faker;
import utils.RandomUtils;

import java.util.HashMap;

import static utils.RandomUtils.*;

public class TestData {
    Components components = new Components();
    RandomUtils randomUtils = new RandomUtils();
    //ADMIN CREDS
//    public static String adminLogin = "n.brevnov@belvg.com";
//    public static String adminPassword = "n.brevnovBelVG$ecuRiTy2021";

    //CREATE USER
    Faker faker = new Faker();
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = firstName + lastName + "@test.com";
    public String password = randomUtils.getRandomString(16);
    public String mobileNumber = randomUtils.getRandomLong(00000000L, 99999999L).toString();

    //PAGES
    String homePage = "";
    public String createUserPage = "/customer/account/create";
    public String loginPage = "/customer/account/login";
    public String contactPage = "/info/kontakt";
    public String vinduerCatalogPage = "/vinduer";
    public String doreCatalogPage = "/doere";
    public String terrassedoreCatalogPage = "/terrassedoere";
    public String skydedoreCatalogPage = "/skydedoere";
    public String adminPage = "/skanvacms/admin";
    public String addToCartPage = "/tr/topstyret-vindue-2-fags";

    public HashMap getAddToCartParams() {
    HashMap<String, String> addToCartParams = new HashMap<>();
    addToCartParams.put("product", "4404");
    addToCartParams.put("item", "4404");
    addToCartParams.put("qty", "1");
    addToCartParams.put("options[60382]", "{\"height\":120,\"width\":190,\"width1\":95,\"fieldwidth2\":95}");
        return addToCartParams;
    }
}
