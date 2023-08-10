Feature: Validating G|Clubs API's
Scenario Outline: Verify Member able to login G|Clubs application & get SID
  Given "<loginUser>" payloads with "<userName>" and "<password>"
  When user calls "loginMemberAPI" API with "POST" https request 
  Then the API response with the success
  And get the Cookie's SID
  And validate the response with key "state" and value "success"
  
  Examples:
   |loginUser|userName                       |password       |
   |Member   |shiva4.test+134_@gmail.com     |Shiva@1212     | 

Scenario Outline: Verify Manager's able to login ERP application & get SID
  Given "<loginUser>" payloads with "<userName>" and "<password>"
  When user calls "loginManagerAPI" API with "POST" https request 
  Then the API response with the success
  And get the Cookie's SID
  And validate the response with key "nickname" and value "<loginUser>"
  
  Examples:
  |loginUser     |userName                           |password      |
  |Admin         |admin                              |admin123      |
  |Finance       |ShivaFinance1                      |Shiva@123     |
 #|Privacy       |ShivaPrivacy                       |Shiva@1212    |
 #|Supervisors   |ShivaSupervisors                   |Shiva@1212    |
 #|Agents        |ShivaAgents                        |Shiva@1212    |


Scenario Outline: Verify placeOrder MT
  Given placeOrder payloads with "<itemCode>" , "<quantity>", "<membershipId>", "<payClassify>", "<payAccountName>", "<payAccountId>"
  When user calls "StorefrontAPI" API with "POST" https request 
  Then the API response with the success 
  And get the OrderId
  And validate the response with key "data.placeOrder" and value "orderNo"  
  
   Examples:
    |itemCode      |quantity  |payClassify      |payAccountName       |payAccountId          |
    |MT1           |1         |Cheque           |                     |                      |
   #|MT1           |1         |HDollar          |Shiva                |shiva@gmail.com       |

Scenario Outline: Verify MTOrder dual confirmation by Admin & Finance  
  Given "<loginUser>", "<OrderType>" and "<itemCode>" confirmPayment payloads with <receivedAmount>
  When user calls "ERPAPI" API with "POST" https request 
  Then the API response with the success 
  And validate the response with key "data.confirmPayment" and value "true"
   Examples:
    |loginUser    |OrderType         |itemCode          |receivedAmount  |
    |Admin        |MTOrder           |MT1               |1000000         |
    |Finance      |MTOrder           |MT1               |1000000         |

Scenario Outline: Verify getMember info 
  Given getListMember payload with <page> and <perPage>
  When user calls "StorefrontAPI" API with "POST" https request 
  Then the API response with the success 
  And get MT MembershipId 
  
   Examples:
    |page   |perPage |
    |1      |200     |
    
Scenario Outline: Verify placeOrder MR
  Given placeOrder payloads with "<itemCode>" , "<quantity>", "<membershipId>", "<payClassify>", "<payAccountName>", "<payAccountId>"
  When user calls "StorefrontAPI" API with "POST" https request 
  Then the API response with the success 
  And get the OrderId 
  And validate the response with key "data.placeOrder" and value "orderNo"  
  
   Examples:
    |itemCode      |quantity  |payClassify      |payAccountName       |payAccountId          |membershipId |
    |MR1           |1         |Cheque           |                     |                      |             |
   #|MR1           |1         |HDollar          |Shiva                |shiva@gmail.com       |             |

   
Scenario Outline: Verify MROrder dual confirmation by Admin & Finance  
  Given "<loginUser>", "<OrderType>" and "<itemCode>" confirmPayment payloads with <receivedAmount>
  When user calls "ERPAPI" API with "POST" https request 
  Then the API response with the success
  And validate the response with key "data.confirmPayment" and value "true" 
  
   Examples:
    |loginUser    |OrderType         |itemCode          |receivedAmount  |
    |Admin        |MROrder           |MR1               |50000           |
    |Finance      |MROrder           |MR1               |50000           |
    
Scenario Outline: Verify placeOrder MU
  Given placeOrder payloads with "<itemCode>" , "<quantity>", "<membershipId>", "<payClassify>", "<payAccountName>", "<payAccountId>"
  When user calls "StorefrontAPI" API with "POST" https request 
  Then the API response with the success 
  And get the OrderId
  And validate the response with key "data.placeOrder" and value "orderNo"   
  
   Examples:
    |itemCode      |quantity  |payClassify      |payAccountName       |payAccountId          |membershipId |
    |MU12          |1         |Cheque           |                     |                      |             |
   #|MU12          |1         |HDollar          |Shiva                |shiva@gmail.com       |             |      

   
Scenario Outline: Verify MUOrder dual confirmation by Admin & Finance  
  Given "<loginUser>", "<OrderType>" and "<itemCode>" confirmPayment payloads with <receivedAmount>
  When user calls "ERPAPI" API with "POST" https request 
  Then the API response with the success 
  And validate the response with key "data.confirmPayment" and value "true"
  
   Examples:
    |loginUser    |OrderType         |itemCode          |receivedAmount  |
    |Admin        |MUOrder           |MU12              |1000000         |
    |Finance      |MUOrder           |MU12              |1000000         |
