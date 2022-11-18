package tests.user;

import static com.codeborne.selenide.Selenide.open;

public class Components {
    public static String basicAuthLogin = "admin";
    public static String basicAuthPassword = "BilarIsgreaT2020!%23";

    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }
}
