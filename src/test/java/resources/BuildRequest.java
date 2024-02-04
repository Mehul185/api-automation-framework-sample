package resources;

import java.util.ArrayList;

import payloads.Request.AddPlaceRequest;
import payloads.Request.Location;

public class BuildRequest {
	
	public AddPlaceRequest addPlaceRequestStructure(String name, String language, String address) {
		AddPlaceRequest addPlaceRequest = new AddPlaceRequest();
		addPlaceRequest.setAccuracy(50);
		addPlaceRequest.setName(name);
		addPlaceRequest.setPhone_number("(+91) 983 893 3937");
		addPlaceRequest.setAddress(address);
		addPlaceRequest.setWebsite("http://google.com");
		addPlaceRequest.setLanguage(language);
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		addPlaceRequest.setLocation(location);
		
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		addPlaceRequest.setTypes(types);
		
		return addPlaceRequest;
	}
	
	public String deletePlaceRequestStructure(String placeId) {
		
		return "{\"place_id\":\""+placeId+"\"}";
	}

}
