package tests.data;

import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticData.basicAuthLogin;
import static tests.data.StaticData.basicAuthPassword;

public class StaticMethods {
    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }
}
