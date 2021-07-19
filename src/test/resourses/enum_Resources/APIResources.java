package enum_Resources;

public enum APIResources {

	
	AddPlaecAPI("/maps/api/place/add/json"),
	GetPlaecAPI("/maps/api/place/get/json"),
	DeletePlaecAPI("/maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
