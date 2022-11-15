package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.APIListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static usertests.Components.basicAuthLogin;
import static usertests.Components.basicAuthPassword;

public class Specs {
    public static RequestSpecification requestSpecification1 = with()
            .auth()
            .basic(basicAuthLogin, basicAuthPassword)
            .header("authorization", "Basic YWRtaW46QmlsYXJJc2dyZWFUMjAyMCEj")
            .header("x-requested-with", "XMLHttpRequest")
            .header("Connection", "keep-alive")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
            //           .filter(withCustomTemplates())
            .log().uri()
            .log().cookies();
    public static ResponseSpecification responseSpecification1 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .log(LogDetail.HEADERS)
            .log(LogDetail.COOKIES)
            .build();
}
