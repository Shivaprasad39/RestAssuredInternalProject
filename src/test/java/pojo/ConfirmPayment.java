package pojo;

public class ConfirmPayment {
	private String operationName;	
	private ConfirmPaymentVariables variables;
	private String query;
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public ConfirmPaymentVariables getVariables() {
		return variables;
	}
	public void setVariables(ConfirmPaymentVariables variables) {
		this.variables = variables;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
    
}
