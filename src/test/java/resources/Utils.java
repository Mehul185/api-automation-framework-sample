package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	RequestSpecification addPlaceReqSpec;
	ResponseSpecification addPlaceResSpec;

	public RequestSpecification requestSpecificaion() throws IOException {
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss-ms");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String destinationPath = "output/logging_"+formattedDate+".txt";
	    
		PrintStream log = new PrintStream(new FileOutputStream(destinationPath));
		
		addPlaceReqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).setContentType(ContentType.JSON).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return addPlaceReqSpec;
	}
	
	public ResponseSpecification responseSpecification(Integer statusCode) {
		addPlaceResSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(statusCode).build();
		return addPlaceResSpec;
	}
	
	public static String getGlobalValues(String value) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Mehul\\eclipse-workspace\\ApiFrameworkTestingPractice\\APIFramework\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);		
		return properties.getProperty(value);
		
	}
	
	public String getResponseValue(String response, String key) {
		JsonPath jp = new JsonPath(response);
		return jp.get(key).toString();
	}
	
}
