package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.ApiResources;
import resources.BuildRequest;
import resources.Utils;

public class stepDefinition extends Utils{
	
	BuildRequest buildRequest = new BuildRequest();
	RequestSpecification req;
	Response response;
	static String placeId;
		
	@Given("The payload for add place api with {string} {string} {string}")
	public void the_payload_for_add_place_api(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions		
		req = given().spec(requestSpecificaion()).body(buildRequest.addPlaceRequestStructure(name,language,address));
	}
	
	@When("The user calls {string} with {string} request")
	public void the_user_calls_with_request(String resource, String httpMethod) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		ApiResources apiResource = ApiResources.valueOf(resource);
		if(httpMethod.equalsIgnoreCase("POST"))
			response = req.when().post(apiResource.getResource());
		else if(httpMethod.equalsIgnoreCase("GET"))
			response = req.when().get(apiResource.getResource());
		else if(httpMethod.equalsIgnoreCase("PUT"))
			response = req.when().put(apiResource.getResource());
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			response = req.when().delete(apiResource.getResource());
	}
	
	@Then("Validate that the status code is {int}")
	public void validate_that_the_status_code_is(Integer statusCode) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),statusCode.intValue());
	}
	
	@Then("Validate that {string} is {string}")
	public void validate_that_is(String responseKey, String responseValue) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(getResponseValue(response.asString(),responseKey),responseValue);
	}
	
	@Then("verify that the place_id created maps to {string} using {string} call")
	public void verify_that_the_place_id_created_maps_to_using_call(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		placeId = getResponseValue(response.asString(),"place_id");
		req = given().spec(requestSpecificaion()).queryParam("place_id", placeId);	
		the_user_calls_with_request(resource, "GET");				
		String actualName = getResponseValue(response.asString(),"name");				
		assertEquals(actualName,expectedName);			    
	}
	
	@Given("Delete Place Api Payload")
	public void delete_place_api_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    req = given().spec(requestSpecificaion()).body(buildRequest.deletePlaceRequestStructure(placeId));
	}	
}
