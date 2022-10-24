package usertests;

import com.github.javafaker.Faker;
import static Utils.RandomUtils.*;

public class TestData {
    //ADMIN CREDS
    static String adminLogin = "n.brevnov@belvg.com";
    static String adminPassword = "n.brevnovBelVG$ecuRiTy2021";

    //CREATE USER
    static Faker faker = new Faker();
    static String firstName = faker.name().firstName();
    static String lastName = faker.name().lastName();
    static String email = firstName + lastName + "@test.com";
    static String password = getRandomString(16);

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


}
