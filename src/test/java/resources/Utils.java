package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification req;
    static RequestSpecification reqerp;
	
	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
		RestAssured.baseURI = globalValue("baseUrl");			
		PrintStream log = new PrintStream(new FileOutputStream("loggingSF.txt"));
		req = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
		}
		return req;
	}
	
   public RequestSpecification requestSpecificationerp() throws IOException {
		if (reqerp == null) {
		RestAssured.baseURI = globalValue("erpBaseuRL");
		PrintStream log = new PrintStream(new FileOutputStream("loggingERP.txt"));
		reqerp = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqerp;
		}
		return reqerp;
	}
   
   public String getJsonPath (Response response, String Key) {
	   String resp = response.asString();
	   JsonPath js = new JsonPath(resp);
	   String keyValue = js.get(Key);
	   return keyValue;
   }
   public boolean getJsonPathboolean (Response response, String Key) {
	   String resp = response.asString();
	   JsonPath js = new JsonPath(resp);
	   boolean keyValue = js.get(Key);
	   return keyValue;
   }
	
	public String globalValue(String key) throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Inidum Software\\Documents\\QA Training\\QA_Training_Java\\NewProject\\Storefront_API\\src\\test\\java\\resources\\Global.properties");
		p.load(fis);
		return p.getProperty(key);
	}

}
