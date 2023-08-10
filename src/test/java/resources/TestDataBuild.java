package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.ConfirmPayment;
import pojo.ConfirmPaymentVariables;
import pojo.GetListMembership;
import pojo.PlaceOrder;
import pojo.items;
import pojo.listMemberVariables;
import pojo.loginManager;
import pojo.loginMember;
import pojo.paymentMethod;
import pojo.variables;

public class TestDataBuild {

   public loginMember loginMemberPayload(String userName, String password) {
	   loginMember l = new loginMember();
	   l.setEmail(userName);
	   l.setPassword(password);
	   return l;
   }
   
   public loginManager loginManagerPayload(String userName, String password) {
	   loginManager m = new loginManager();
	   m.setAccount(userName);
	   m.setPassword(password);
	   return m;
   }
   
  public PlaceOrder placeOrderPayload(String itemCode, String quantity, String membershipId, String payClassify, String payAccountName, String payAccountId) {
	  PlaceOrder p = new PlaceOrder();
	  p.setOperationName("placeOrder");
	  p.setVariables(null);
	  variables var = new variables();
	  items i = new items();
	  i.setItemCode(itemCode);
	  i.setQuantity(quantity);
	  i.setMembershipId(membershipId);
	  List<items> listItems = new ArrayList<items>();
	  listItems.add(i);
	  var.setItems(listItems);  
	  paymentMethod m = new paymentMethod();
	  m.setPayClassify(payClassify);
	  m.setPayAccountName(payAccountName);
	  m.setPayAccountId(payAccountId);
	  var.setPaymentMethod(m);
	  p.setVariables(var);
	  p.setQuery("mutation placeOrder($items: [itemInput]!, $paymentMethod: paymentMethodInput!) {\n  placeOrder(items: $items, paymentMethod: $paymentMethod)\n}\n");
	  return p;
  }
  public ConfirmPayment confirmPayment(String orderId, int receivedAmount) {
	  ConfirmPayment c = new ConfirmPayment();
	  c.setOperationName("ConfirmPayment");	  
	  ConfirmPaymentVariables var = new ConfirmPaymentVariables();
	  var.setOrderId(orderId);
	  var.setReceivedAmount(receivedAmount);
	  c.setVariables(var);
	  c.setQuery("mutation ConfirmPayment($orderId: String!, $receivedAmount: Int) {\n  confirmPayment(orderId: $orderId, receivedAmount: $receivedAmount)\n}\n");
	  return c;
	  
  }
  public GetListMembership listMember(int page, int perPage) {
	  GetListMembership l = new GetListMembership();
	  l.setOperationName("getListMembershipDetails");
	  listMemberVariables var = new listMemberVariables();
	  var.setPage(page);
	  var.setPerPage(perPage);
	  l.setVariables(var);	  
	  l.setQuery("query getListMembershipDetails($status: cardStatusEnumType, $perPage: Int!, $page: Int!) {\n  listMembershipDetails(status: $status, perPage: $perPage, page: $page) {\n    count\n    list {\n      id\n      card\n      createTime\n      expiryTime\n      level\n      status\n      orderNo\n      boundAccounts {\n        appAccount\n        application\n        id\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n}\n");
	  return l;
  }


}
