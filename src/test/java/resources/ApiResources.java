package resources;

public enum ApiResources {	
	
	AddPlaceApi("/maps/api/place/add/json"),
	GetPlaceApi("/maps/api/place/get/json"),
	PutPlaceApi("/maps/api/place/update/json"),
	DeletePlaceApi("/maps/api/place/delete/json");
	private String resource;
	
	ApiResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;		
	}
	
	public String getResource() {
		return resource;
	}
}
