package rest_assured.endpoints.keywords;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import rest_assured.globals.TokenGlobal;

public class SpecBuilder {
    public static RequestSpecification getRequestSpecBuilder(){
        return new RequestSpecBuilder()
                .addHeader("Authorization","Bearer "+ TokenGlobal.TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getRequestSpecNoAuthBuilder(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification getResponseSpecBuilder(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
