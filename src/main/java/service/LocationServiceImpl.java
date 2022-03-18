package service;

import model.prod.Location;
import repositories.LocationRepository;


public class LocationServiceImpl {

	LocationRepository locationRepo;

	public LocationServiceImpl (LocationRepository locationRepo)
	{
		this.locationRepo = locationRepo;
	}

	public Location createLocation(Location location) {
		locationRepo.save(location);
		return locationRepo.save(location);
	}

	public Location getLocation(int id) {
		return locationRepo.findById(id).get();
	}

	public Location updateLocation(Location location) {
		if(locationRepo.existsById(location.getLocationid())) 
			return locationRepo.save(location);
		else throw new IllegalArgumentException();
	}

	public void deleteOrder(int id) {
		locationRepo.deleteById(id);
	}
}
