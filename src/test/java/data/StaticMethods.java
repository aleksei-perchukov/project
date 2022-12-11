package data;

import static com.codeborne.selenide.Selenide.open;
import static data.StaticData.basicAuthLogin;
import static data.StaticData.basicAuthPassword;

public class StaticMethods {
    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }
}
