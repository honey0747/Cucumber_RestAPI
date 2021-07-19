package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.gherkin.model.ScenarioOutline;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {

	static RequestSpecification reqSP;
	ResponseSpecification resSP;
	
	public String getStringValueFromStringResponse(String response, String field) {
		JsonPath js=new JsonPath(response);
		return js.getString(field);
	}
	
	public String getValueFromResponse(Response response, String field) {
		JsonPath js=new JsonPath(response.asString());
		return js.get(field).toString();
	}
	
	public RequestSpecification getRequestSpecificationBuilder(String baseURI) throws FileNotFoundException {
		if(reqSP==null) {
		PrintStream log=new PrintStream(new FileOutputStream("Logs\\logging_"+getDate()+".txt"));		
		reqSP= new RequestSpecBuilder()
				.setBaseUri(baseURI)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		}
		return reqSP;
	}
	
	
	public ResponseSpecification getResponseSpecificationBuilder(int statusCode) {
		resSP= new ResponseSpecBuilder()
				.expectStatusCode(statusCode)
				.expectContentType(ContentType.JSON).build();
		return resSP;
	}
	
	public String getGlobalProperties(String key) throws IOException {
		Properties prop= new Properties();
		FileInputStream fis=new FileInputStream("src\\test\\resourses\\properties\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getDate() {
		return  new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss").format(Calendar.getInstance().getTime());
	}
	
}
