package org.epam.poland.aqa.model.heirs.responses.heirs;

import io.restassured.response.Response;
import org.epam.poland.aqa.model.heirs.responses.RsObject;

public class PostResponseObject extends RsObject {
    private static final String RESULT_ID_LOCATOR = "id";
    private static final String RESULT_USERNAME_LOCATOR = "username";

    public PostResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public String getUsername() {
        return response.jsonPath().get(RESULT_USERNAME_LOCATOR);
    }
}
