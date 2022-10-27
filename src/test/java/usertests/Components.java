package usertests;

import com.codeborne.selenide.AuthenticationType;

import static com.codeborne.selenide.Selenide.open;

public class Components {
    static String basicAuthLogin = "admin";
    static String basicAuthPassword = "BilarIsgreaT2020!%23";

    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }
}
