package stepDefinitions;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GetListMembershipResponse;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.*;

public class stepDefinitions extends Utils {
	TestDataBuild datas = new TestDataBuild();
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	Response response;
	static String MemberSID;
	static String AdminSID;
	static String FinanceSID;
	static String MTplaceOrderId;
	static String MRplaceOrderId;
	static String MUplaceOrderId;
	static String membershipId;
	String User;
	String itemCode;
	String ExpectedValue;

		
	@Given("{string} payloads with {string} and {string}")
	public void payloads_with_and(String loginUser, String userName, String password) throws IOException {
	    User = loginUser;
		if(loginUser.equalsIgnoreCase("Member") ) 
		{
			reqspec = RestAssured
				 .given()				
				 .spec(requestSpecification())
				 .body(datas.loginMemberPayload(userName, password ));
					
		}
		else if (loginUser.equalsIgnoreCase("Admin") || loginUser.equalsIgnoreCase ("Finance" ))
	   {
			reqspec = RestAssured
					 .given()				
					 .spec(requestSpecificationerp())
					 .body(datas.loginManagerPayload(userName, password ));
		}
	}
	
	@When("user calls {string} API with {string} https request")
	public void user_calls_api_with_https_request(String resource, String method) {
	resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	APIResource resourceAPI = APIResource.valueOf(resource);
	response = reqspec.when().post(resourceAPI.getResource());
	}
	@Then("the API response with the success")
	public void the_api_response_with_the_success() {
		assertEquals(response.getStatusCode(), 200);
	}	
	@Then("get the Cookie's SID")
	public void get_the_cookie_s_sid() {
			if(User.equalsIgnoreCase("Member")) {
				 MemberSID = response.header("Set-Cookie");
			System.out.println("MemberSID = "+ MemberSID);
			}
			else if(User.equalsIgnoreCase("Admin")) {
				 AdminSID = response.header("Set-Cookie");
			System.out.println("AdminSID = "+ AdminSID);
			}
			else if(User.equalsIgnoreCase("Finance")) {
				 FinanceSID = response.header("Set-Cookie");
			System.out.println("FinanceSID = "+ FinanceSID);
			}				
		}	
	@Given("placeOrder payloads with {string} , {string}, {string}, {string}, {string}, {string}")
	public void place_order_payloads_with(String itemCode, String quantity, String membershipId, String payClassify, String payAccountName, String payAccountId) throws IOException {
		this.itemCode = itemCode;
		if (itemCode.contains("MT")) {
		membershipId = "";		
		reqspec = RestAssured
				 .given()				
				 .spec(requestSpecification())
				 .cookie(MemberSID)
				 .body(datas.placeOrderPayload(itemCode, quantity, membershipId, payClassify, payAccountName, payAccountId));
		}
		else {
		membershipId = stepDefinitions.membershipId;		
		reqspec = RestAssured
				 .given()				
				 .spec(requestSpecification())
				 .cookie(MemberSID)
				 .body(datas.placeOrderPayload(itemCode, quantity, membershipId, payClassify, payAccountName, payAccountId));
	}	
	}
	@Then("get the OrderId")
	public void get_the_order_id() {
		if (itemCode.contains("MT")) {
		String placeOrderId = getJsonPath (response, "data.placeOrder");
	    MTplaceOrderId = placeOrderId.substring(10);
   	    System.out.println("MTplaceOrderId : "+ MTplaceOrderId);
		}
		else if (itemCode.contains("MR")) {
		String placeOrderId = getJsonPath (response, "data.placeOrder");
		MRplaceOrderId = placeOrderId.substring(10);
   	    System.out.println("MRplaceOrderId : "+ MRplaceOrderId);
		}
		else if (itemCode.contains("MU")) {
		String placeOrderId = getJsonPath (response, "data.placeOrder");
		MUplaceOrderId = placeOrderId.substring(10);
   	    System.out.println("MUplaceOrderId : "+ MUplaceOrderId);
		}
	} 
	@Given("{string} and {string} confirmPayment payloads with {int}")
	public void and_confirm_payment_payloads_with(String loginUser, String itemCode, Integer receivedAmount) throws IOException {
		if (loginUser.equalsIgnoreCase("Admin") )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(AdminSID)
						 .body(datas.confirmPayment(MTplaceOrderId, receivedAmount));
				System.out.println("Admin first approval done");				
			}
		else if (loginUser.equalsIgnoreCase("Finance") )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(FinanceSID)
						 .body(datas.confirmPayment(MTplaceOrderId, receivedAmount));
				System.out.println("Finance second approval done");
			}		
	}
	@Given("{string}, {string} and {string} confirmPayment payloads with {int}")
	public void and_confirm_payment_payloads_with(String loginUser, String OrderType, String itemCode, Integer receivedAmount) throws IOException {
		if (loginUser.equalsIgnoreCase("Admin") && OrderType.equalsIgnoreCase("MTOrder")  )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(AdminSID)
						 .body(datas.confirmPayment(MTplaceOrderId, receivedAmount));
				System.out.println("Admin first approval done");				
			}
		else if (loginUser.equalsIgnoreCase("Finance") && OrderType.equalsIgnoreCase("MTOrder") )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(FinanceSID)
						 .body(datas.confirmPayment(MTplaceOrderId, receivedAmount));
				System.out.println("Finance second approval done");
			}	
		if (loginUser.equalsIgnoreCase("Admin") && OrderType.equalsIgnoreCase("MROrder")  )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(AdminSID)
						 .body(datas.confirmPayment(MRplaceOrderId, receivedAmount));
				System.out.println("Admin first approval done");				
			}
		else if (loginUser.equalsIgnoreCase("Finance") && OrderType.equalsIgnoreCase("MROrder"))
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(FinanceSID)
						 .body(datas.confirmPayment(MRplaceOrderId, receivedAmount));
				System.out.println("Finance second approval done");
			}
		if (loginUser.equalsIgnoreCase("Admin") && OrderType.equalsIgnoreCase("MUOrder")  )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(AdminSID)
						 .body(datas.confirmPayment(MUplaceOrderId, receivedAmount));
				System.out.println("Admin first approval done");				
			}
		else if (loginUser.equalsIgnoreCase("Finance") && OrderType.equalsIgnoreCase("MUOrder") )
		   {
				reqspec = RestAssured
						 .given()				
						 .spec(requestSpecificationerp())
						 .cookie(FinanceSID)
						 .body(datas.confirmPayment(MUplaceOrderId, receivedAmount));
				System.out.println("Finance second approval done");
			}
	}
	
  /*@Then("validate the response is {string}")
    public void validate_the_response_is(String ExpectedValue) {
  	    	String ActualValue = getJsonPath (response, "state");
  	    	System.out.println("Actual" + ActualValue);
  	    	if(ActualValue.contains(ExpectedValue)){
  	    		this.ExpectedValue = ActualValue;
  	    		System.out.println("Expected value after change" + this.ExpectedValue);
  	    	}
  	        assertEquals(ActualValue, this.ExpectedValue);
		}*/
    @Then("validate the response with key {string} and value {string}")
    public void validate_the_response_with_key_and_value(String key, String ExpectedValue) {
        if(ExpectedValue.equalsIgnoreCase("true") || ExpectedValue.equalsIgnoreCase("false")) {
        	boolean ExpectedBooleanValue = Boolean.parseBoolean(ExpectedValue);
        	boolean ActualBooleanValue = getJsonPathboolean(response, key);
        	assertEquals(ActualBooleanValue, ExpectedBooleanValue);
        }
        else {
    	String ActualValue = getJsonPath (response, key);    	
	    	if(ActualValue.contains(ExpectedValue) || ActualValue.equalsIgnoreCase(ExpectedValue)){
  	    		this.ExpectedValue = ActualValue;  	    		
  	    	}
  	        assertEquals(ActualValue, this.ExpectedValue);
        }
    }   
    
  	@Given("getListMember payload with {int} and {int}")
  	public void get_list_member_payload_with_and(Integer page, Integer perPage) throws IOException {
  		reqspec = RestAssured
				 .given()				
				 .spec(requestSpecification())
				 .cookie(MemberSID)
				 .body(datas.listMember(page, perPage ));
  	}  	
 	@Then("get MT MembershipId")
  	public void get_mt_membership_id() {
 	  int listLength = response.as(GetListMembershipResponse.class).getData().getListMembershipDetails().getList().size();
 	  System.out.println("MembershipListCount : " + listLength);
 	  membershipId = response.as(GetListMembershipResponse.class).getData().getListMembershipDetails().getList().get(listLength-1).getCard();
 	  System.out.println("MembershipId : " + membershipId);
  	}
	}

