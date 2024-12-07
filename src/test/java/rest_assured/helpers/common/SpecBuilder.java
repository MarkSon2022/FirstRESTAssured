package rest_assured.helpers.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static String TOKEN = "";

    public static RequestSpecification getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpecNoAuthBuilder() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

}
