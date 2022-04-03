package org.epam.poland.aqa.model.heirs.requests.heirs;

import org.epam.poland.aqa.model.heirs.requests.RqObject;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForGettingPetsByStatus(String status) {
        setBaseUri();
        setBasePath("pet/findByStatus");
        setParam("status", status);
        setCommonParams();
    }

    public void createRequestForGettingPetsById(String id) {
        setBaseUri();
        setBasePath("pet/" + id);
        setCommonParams();
    }

    public void createRequestForGettingUserByUsername(String username) {
        setBaseUri();
        setBasePath("user/" + username);
        setCommonParams();
    }
}
