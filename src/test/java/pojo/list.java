package pojo;

import java.util.List;

public class list {
	private String __typename;
	private List<String> boundAccounts;
	private String card;
	private long createTime;
	private long expiryTime;
	private String id;
	private int level;
	private String orderNo;
	private String status;
	public String get__typename() {
		return __typename;
	}
	public void set__typename(String __typename) {
		this.__typename = __typename;
	}
	public List<String> getBoundAccounts() {
		return boundAccounts;
	}
	public void setBoundAccounts(List<String> boundAccounts) {
		this.boundAccounts = boundAccounts;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
