package testDataBuild_Payloads;

import java.util.ArrayList;
import java.util.List;

import pojo_Payload.AddPlace;
import pojo_Payload.Location;

public class TestDataBuild {

	AddPlace ap=new AddPlace();
	Location lc=new Location();
	
	public AddPlace addPlacePayload() {
		List<String> types=new ArrayList<String>();
		types.add("Home");
		types.add("shop");
		lc.setLat(38.454335);
		lc.setLng(30.345345);
		ap.setAccuracy(70);
		ap.setAddress("Apna Ghar");
		ap.setLanguage("English");
		ap.setLocation(lc);
		ap.setName("Jain");
		ap.setPhone_number("123456744");
		ap.setTypes(types);
		ap.setWebsite("WWW.poi.com");
		
		return ap;
	}
	
	//Updated : To make data driven
	public AddPlace addPlacePayload(String name, String lang,String Address) {
		List<String> types=new ArrayList<String>();
		types.add("Home");
		types.add("shop");
		lc.setLat(38.454335);
		lc.setLng(30.345345);
		ap.setAccuracy(70);
		ap.setAddress(Address);
		ap.setLanguage(lang);
		ap.setLocation(lc);
		ap.setName(name);
		ap.setPhone_number("123456744");
		ap.setTypes(types);
		ap.setWebsite("WWW.poi.com");
		
		return ap;
	}
	
	public String getDeletePlacePayload(String placeID) {
		return "{\r\n\"place_id\": \""+placeID+"\"\r\n}";
	}
}
