package org.epam.poland.aqa.model.heirs.requests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.epam.poland.aqa.model.RxObject;

import java.util.HashMap;
import java.util.Map;

public class RqObject extends RxObject {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    protected RequestSpecification requestSpecification;

    public RqObject(String rqName) {
        super(rqName);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    protected void setBaseUri() {
        setBaseUri(BASE_URI);
    }

    protected void setBaseUri(String uri) {
        requestSpecification.baseUri(uri);
    }

    protected void setBasePath(String path) {
        requestSpecification.basePath(path);
    }

    protected void setParam(String key, String value) {
        requestSpecification.param(key, value);
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }

    public Response sendGetRequest() {
        return requestSpecification.get();
    }

    public Response sendPostRequest() {
        return requestSpecification.post();
    }

}
