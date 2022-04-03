package org.epam.poland.aqa.model.heirs.requests.heirs;

import org.epam.poland.aqa.model.heirs.requests.RqObject;
import org.json.JSONException;
import org.json.JSONObject;

public class PostRequestObject extends RqObject {
    public PostRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForCreatingUser(String username, String firstName, String lastName, String email,
                                             String password, String phone, int userStatus) throws JSONException {
        setBaseUri();
        setBasePath("user/");
        setCommonParams();
        requestSpecification.body(createUserAsJsonObject(username, firstName, lastName, email, password, phone, userStatus).toString());
    }

    public void createRequestForCreatingPet(String id, String name, String status) throws JSONException {
        setBaseUri();
        setBasePath("pet/");
        setCommonParams();
        requestSpecification.body(createPetAsJsonObject(id, name, status).toString());
    }

    private JSONObject createUserAsJsonObject(String username, String firstName, String lastName, String email,
                                              String password, String phone, int userStatus) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("phone", phone);
        requestBody.put("userStatus", userStatus);
        return requestBody;
    }

    private JSONObject createPetAsJsonObject(String id, String name, String status) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("name", name);
        requestBody.put("status", status);
        return requestBody;
    }
}
