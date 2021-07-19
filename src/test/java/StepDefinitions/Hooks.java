package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	APIStepDefinition aPIStepDefinition=new APIStepDefinition();
	
	@Before("@DeletePlace_UsingHooks")
	public void addPlaceHook() throws  IOException {
		//it will run before deletePlace TC but only when if place id is null
		//[means we need not to worry about running add place api TCs before delete place api TC]
		
		if(APIStepDefinition.placeID==null) {
		aPIStepDefinition.add_place_payload_with("Honey", "English", "Africa");
		aPIStepDefinition.call_with_http_request("AddPlaecAPI",  "Post");
		aPIStepDefinition.api_call_got_success_with_status_code(200);
		aPIStepDefinition.verify_placeID_got_created_having_using("name", "Honey" , "GetPlaecAPI");	
	}
	}
}
