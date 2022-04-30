package co.edu.icesi.dev.uccareapp.transport.service;

import java.math.BigDecimal;


import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Location;
import co.edu.icesi.dev.uccareapp.transport.repositories.LocationRepository;

@Service
public class LocationServiceImpl {

	private LocationRepository locationRepo;

	public LocationServiceImpl (LocationRepository locationRepo)
	{
		this.locationRepo = locationRepo;
	}

	public Location addLocation(Location location) throws Exception {

		if (location.getName() == null | location.getName().length() < 5)
			throw new Exception("Location's name must be atleast 5 characters");

		if (location.getAvailability() == null)
			throw new Exception("Availibility can't be null");

		if (location.getAvailability().compareTo(new BigDecimal(1)) < 0 | location.getAvailability().compareTo(new BigDecimal(10)) > 0)
			throw new Exception("Availibility must fall within range (1, 10)");

		if (location.getCostrate() == null)
			throw new Exception("Cost rate can't be null");

		if (location.getCostrate().compareTo(new BigDecimal(0)) < 0 | location.getCostrate().compareTo(new BigDecimal(1)) > 0)
			throw new Exception("Cost rate must fall within range (0, 1)");
		
		return locationRepo.save(location);
	}

	public Location editLocation(Location location) throws Exception {
		if(locationRepo.existsById(location.getLocationid())) {

			if (location.getName() == null | location.getName().length() < 5)
				throw new Exception("Location's name must be atleast 5 characters");

			if (location.getAvailability() == null)
				throw new Exception("Availibility can't be null");

			if (location.getAvailability().compareTo(new BigDecimal(1)) < 0 | location.getAvailability().compareTo(new BigDecimal(10)) > 0)
				throw new Exception("Availibility must fall within range (1, 10)");

			if (location.getCostrate() == null)
				throw new Exception("Cost rate can't be null");

			if (location.getCostrate().compareTo(new BigDecimal(0)) < 0 | location.getCostrate().compareTo(new BigDecimal(1)) > 0)
				throw new Exception("Cost rate must fall within range (0, 1)");

			return locationRepo.save(location);
		}
		else throw new IllegalArgumentException();
	}
}
