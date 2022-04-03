package org.epam.poland.aqa.model.heirs.responses.heirs;

import io.restassured.response.Response;
import org.epam.poland.aqa.model.heirs.responses.RsObject;

public class DeleteResponseObject extends RsObject {
    public DeleteResponseObject(String rsName, Response response) {
        super(rsName, response);
    }
}
