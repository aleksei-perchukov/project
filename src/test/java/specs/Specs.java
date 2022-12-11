package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.APIListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
        ;
import static tests.data.StaticData.*;

public class Specs {
    public static RequestSpecification requestSpecification1 = with()
            .auth()
            .basic("admin", "BilarIsgreaT2020!%23")
  //          .header("authorization", authHeaderValue)
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

    public static RequestSpecification adminRequestSpecification = with()
            .auth()
            .basic("admin", "BilarIsgreaT2020!%23")
            .header("Authorization", "Basic YWRtaW46QmlsYXJJc2dyZWFUMjAyMCEj")
            .contentType("application/json")
            .baseUri(mainUrl)
            .filter(withCustomTemplates())
            .log().uri()
            .log().cookies();

    public static ResponseSpecification adminResponseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.COOKIES)
            .log(LogDetail.HEADERS)
            .log(LogDetail.BODY)
            .build();}
