package resources;

public enum APIResource {
	loginMemberAPI("api/v1/loginMember"),
	loginManagerAPI("api/v1/loginManager"),
	StorefrontAPI("api/graphql"),
	ERPAPI("api/graphqlManager");
	private String resource;
	
	APIResource(String resource){
		this.resource = resource;
	}
    public String getResource() {
    	return resource;
    }
}
