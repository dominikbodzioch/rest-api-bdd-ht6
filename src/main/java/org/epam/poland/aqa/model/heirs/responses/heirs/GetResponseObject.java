package org.epam.poland.aqa.model.heirs.responses.heirs;

import io.restassured.response.Response;
import org.epam.poland.aqa.model.heirs.responses.RsObject;

public class GetResponseObject extends RsObject {
    private static final String RESULT_NAME_LOCATOR = "name";
    private static final String RESULT_USERNAME_LOCATOR = "username";

    public GetResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public int getIdCount() {
        return response.jsonPath().getList(RESULT_NAME_LOCATOR).size();
    }

    public String getName() {
        return response.jsonPath().get(RESULT_NAME_LOCATOR);
    }

    public String getUsername() {
        return response.jsonPath().get(RESULT_USERNAME_LOCATOR);
    }
}
