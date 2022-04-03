package org.epam.poland.aqa.model.heirs.responses;

import io.restassured.response.Response;
import org.epam.poland.aqa.model.RxObject;

public class RsObject extends RxObject {
    protected Response response;

    public RsObject(String rsName, Response response) {
        super(rsName);
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

}
