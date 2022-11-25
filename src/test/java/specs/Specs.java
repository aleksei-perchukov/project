package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.lang.module.Configuration;

import static helpers.APIListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static tests.checkout.CheckoutData.mainUrl;
import static tests.user.Components.basicAuthLogin;
import static tests.user.Components.basicAuthPassword;

public class Specs {
    public static RequestSpecification requestSpecification1 = with()
            .auth()
            .basic(basicAuthLogin, basicAuthPassword)
            .header("authorization", "Basic YWRtaW46QmlsYXJJc2dyZWFUMjAyMCEj")
            .header("x-requested-with", "XMLHttpRequest")
            .header("Connection", "keep-alive")
            .baseUri(mainUrl)
            .filter(withCustomTemplates())
            .log().uri()
            .log().cookies();
    public static ResponseSpecification responseSpecification1 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .log(LogDetail.HEADERS)
            .log(LogDetail.COOKIES)
            .build();
}
