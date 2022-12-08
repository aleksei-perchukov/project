package tests.user;

import static api.AdminAPIMethods.getAdminTokenAPI;
import static com.codeborne.selenide.Selenide.open;

public class Components {
    public static String basicAuthLogin = System.getProperty("basicAuthLogin");
        //System.getProperty("basicAuthLogin");
    public static String basicAuthPassword = System.getProperty("basicAuthPassword");
            //System.getProperty("basicAuthPassword");
    public static String adminLogin = System.getProperty("adminLogin");
    public static String adminPassword = System.getProperty("adminPassword");
    public static String authHeaderValue = System.getProperty("authHeaderValue");
    public static String adminToken = getAdminTokenAPI();


    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }
}
