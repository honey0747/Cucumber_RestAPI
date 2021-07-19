package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testDataBuild_Payloads.TestDataBuild;
import util.Utilities;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import enum_Resources.APIResources;


public class APIStepDefinition extends Utilities{
	
	RequestSpecification reqSP;
	ResponseSpecification resSP;
	RequestSpecification rq;
	Response rs;
	static String placeID;
	
	TestDataBuild tdb= new TestDataBuild();;


	@Given("Add place payload")
	public void add_place_payload() throws IOException {
		reqSP =getRequestSpecificationBuilder(getGlobalProperties("BaseURI"));
		//resSP =getResponseSpecificationBuilder(200);
		rq=given().spec(reqSP).body(tdb.addPlacePayload());
	}

	@When("call {string} with {string} http request")
	public void call_with_http_request(String resource, String method) {
		//to get value from enum class using constructor and resource name
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		//to make it reusable for all type of methods
		if(method.equalsIgnoreCase("Post")) {
			rs=rq.when().post(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("Get")) {
			rs=rq.when().get(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("Delete")) {
			rs=rq.when().delete(resourceAPI.getResource());
		}
		
		String responseString=rs.asString();
		System.out.println("Response is : "+responseString);
	}

	@When("Api call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer expStatusCode) {
		assertEquals( Integer.valueOf(rs.getStatusCode()), expStatusCode);
		System.out.println("Status code is : "+rs.getStatusCode());
	}

	@Then("{string} field value in response is {string}")
	public void field_value_in_response_is(String field, String expValue) {
		String responseString=rs.asString();
		String actValue=getStringValueFromStringResponse(responseString,field);
		assertEquals(expValue,actValue);
		System.out.println("Value of "+field+" is : "+actValue);
	}
	
	//updated code for data driven
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String lang, String address) throws FileNotFoundException, IOException{
		reqSP =getRequestSpecificationBuilder(getGlobalProperties("BaseURI"));
		rq=given().log().all().spec(reqSP).body(tdb.addPlacePayload(name,lang,address));
	}
	
	@Then("Verify placeID got created {string} having {string} using {string}")
	public void verify_placeID_got_created_having_using(String verifyField, String expValue, String resource) throws FileNotFoundException, IOException {
		placeID=getValueFromResponse(rs, "place_id");
		rq=given().spec(getRequestSpecificationBuilder(getGlobalProperties("BaseURI"))).queryParam("place_id", placeID);
		call_with_http_request(resource,"Get");
		String actValue=getValueFromResponse(rs, verifyField);
		assertEquals(expValue, actValue);
		System.out.println("Value of "+verifyField+" is : "+actValue);
	}
	

@Given("Delete place payload")
public void delete_place_payload() throws IOException {
	rq=given().spec(getRequestSpecificationBuilder(getGlobalProperties("BaseURI"))).body(tdb.getDeletePlacePayload(placeID));
	
}
}
