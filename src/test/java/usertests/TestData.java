package usertests;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Utils.RandomUtils.*;

public class TestData {
    //ADMIN CREDS
    public static String adminLogin = "n.brevnov@belvg.com";
    public static String adminPassword = "n.brevnovBelVG$ecuRiTy2021";

    //CREATE USER
    static Faker faker = new Faker();
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String email = firstName + lastName + "@test.com";
    public static String password = getRandomString(16);
    public static String mobileNumber = getRandomLong(00000000L, 99999999L).toString();

    //PAGES
    static String homePage = "";
    static String createUserPage = "/customer/account/create";
    static String loginPage = "/customer/account/login";
    static String contactPage = "/info/kontakt";
    static String vinduerCatalogPage = "/vinduer";
    static String doreCatalogPage = "/doere";
    static String terrassedoreCatalogPage = "/terrassedoere";
    static String skydedoreCatalogPage = "/skydedoere";
    static String adminPage = "/skanvacms/admin";
    public static String addToCartPage = "/tr/topstyret-vindue-2-fags";
 //   public static String addToCartPage = "/checkout/cart/add/uenc/aHR0cHM6Ly9za2FudmEuZGsvdHIvdG9wc3R5cmV0LXZpbmR1ZS0yLWZhZ3M%2C/product/4404/";

    static public HashMap getAddToCartParams() {
    HashMap<String, String> addToCartParams = new HashMap<>();
    addToCartParams.put("product", "4404");
    addToCartParams.put("item", "4404");
    addToCartParams.put("qty", "1");
    addToCartParams.put("options[60382]", "{\"height\":120,\"width\":190,\"width1\":95,\"fieldwidth2\":95}");
        return addToCartParams;
    }
}
