import io.restassured.RestAssured;
import resources.TestDataBuild;

import static io.restassured.RestAssured.*;

public class Demo {
	

	public static void main(String[] args) {
		TestDataBuild d = new TestDataBuild();
		RestAssured
		    .given().log().all()
		      .baseUri("https://erp-dev.gclubdev.net/")
		      .header("Content-Type", "application/json" )
		      .cookie("SID=rqKQVvRE6wexNsZgGd56XPLbPkuqyRXPCw6uravN89TsuRsm8JfW0nSrhP9hC0OlIE87vxhI9Yg1tgc7XiRinJHd8RRjpG0_X8lFvDOIyg; Path=/; Domain=gclubdev.net; Expires=Wed, 07 Sep 2022 07:04:37 GMT; Max-Age=43200; HttpOnly")
		      .body(d.confirmPayment("ND2209062000201", 1000000))
		     .when().log().all()
		        .post("api/graphqlManager")	
		     .prettyPrint();
		System.out.println("Demo success123");
	
	}

}
