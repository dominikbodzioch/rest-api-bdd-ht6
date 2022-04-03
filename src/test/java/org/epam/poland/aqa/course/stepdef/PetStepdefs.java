package org.epam.poland.aqa.course.stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.epam.poland.aqa.course.store.RxStore;
import org.epam.poland.aqa.model.heirs.requests.RqObject;
import org.epam.poland.aqa.model.heirs.requests.heirs.GetRequestObject;
import org.epam.poland.aqa.model.heirs.requests.heirs.PostRequestObject;
import org.epam.poland.aqa.model.heirs.responses.RsObject;
import org.epam.poland.aqa.model.heirs.responses.heirs.GetResponseObject;
import org.epam.poland.aqa.model.heirs.responses.heirs.PostResponseObject;
import org.testng.Assert;

public class PetStepdefs {
    private final RxStore rexStore = RxStore.getInstance();

    @Given("user has {string} request with {string} pet-status")
    public void userHasRequestWithStatus(String rqName, String status) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetsByStatus(status);
        rexStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @Given("user has {string} with following parameters")
    public void userHasRequestWithFollowingParameters(String rqName, DataTable dataTable) {
        PostRequestObject postRequestObject = new PostRequestObject(rqName);
        String username = dataTable.cell(1, 0);
        String firstName = dataTable.cell(1, 1);
        String lastName = dataTable.cell(1, 2);
        String email = dataTable.cell(1, 3);
        String password = dataTable.cell(1, 4);
        String phone = dataTable.cell(1, 5);
        int userStatus = Integer.parseInt(dataTable.cell(1, 6));
        postRequestObject.createRequestForCreatingUser(username, firstName, lastName, email, password, phone, userStatus);

        rexStore.putDataIntoStore(rqName, postRequestObject);
    }

    @Given("user has {string} request with id {string}")
    public void userHasRequestWithId(String rqName, String id) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetsById(id);
        rexStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @Given("user has {string} request with name {string}")
    public void userHasRequestWithName(String rqName, String username) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingUserByUsername(username);
        rexStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @When("user sends {string} {string} request")
    public void userSendsRequestTypeRqName(String rqMethod, String rqName) {
        RqObject rqObject = (RqObject) rexStore.getDataFromStore(rqName);
        String rsObject = rqName.replace("RQ", "RS");

        //todo: log4j
        rqObject.getRequestSpecification().log().uri();

        RsObject respObj = switch (rqMethod) {
            case "GET" -> new GetResponseObject(rsObject, rqObject.sendGetRequest());
            case "POST" -> new PostResponseObject(rsObject, rqObject.sendPostRequest());
            default -> throw new IllegalArgumentException("No method " + rqMethod);
        };
        rexStore.putDataIntoStore(rsObject, respObj);
    }

    @Then("{string} code is {string}")
    public void responseCodeIs(String rsName, String rsCode) {
        RsObject rsObject = (RsObject) rexStore.getDataFromStore(rsName);
        Assert.assertEquals(rsObject.getStatusCode(), Integer.valueOf(rsCode), "Status code is not as expected");
    }

    @And("there is at least {string} pet in {string} response")
    public void thereIsAtLeastNumOfPetsInResponse(String number, String rsName) {
        GetResponseObject rsObject = (GetResponseObject) rexStore.getDataFromStore(rsName);
        int petsCount = rsObject.getIdCount();
        Assert.assertTrue(petsCount >= Integer.parseInt(number),
                "There should be at least " + number + " pets with 'available' status");
    }

    @And("pet's name returned in {string} contains expected {string}")
    public void petSNameReturnedInResponseContainsExpectedName(String rsName, String expName) {
        GetResponseObject rsObject = (GetResponseObject) rexStore.getDataFromStore(rsName);
        String petName = rsObject.getName();
        Assert.assertEquals(petName, expName, "Pet's name is not the same");
    }

    @And("username returned in {string} contains expected {string}")
    public void usernameReturnedInResponseContainsExpectedUsername(String rsName, String expUsername) {
        GetResponseObject rsObject = (GetResponseObject) rexStore.getDataFromStore(rsName);
        String username = rsObject.getUsername();
        Assert.assertEquals(username, expUsername, "User's name is not the same");
    }

    @And("user has {string} request with username from {string} response")
    public void userHasRequestWithUsernameFromResponse(String rqName, String rsName) {
        PostResponseObject rsObject = (PostResponseObject) rexStore.getDataFromStore(rsName);
        String username = rsObject.getUsername();
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingUserByUsername(username);
        rexStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @Given("user has {string} request with with following parameters")
    public void userHasRequestWithWithFollowingParameters(String rqName, DataTable dataTable) {
        PostRequestObject postRequestObject = new PostRequestObject(rqName);
        String id = dataTable.cell(1, 0);
        String name = dataTable.cell(1, 1);
        String status = dataTable.cell(1, 2);
        postRequestObject.createRequestForCreatingPet(id, name, status);

        rexStore.putDataIntoStore(rqName, postRequestObject);
    }
}
