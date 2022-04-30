package co.edu.icesi.dev.uccareapp.transport.service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Location;

public interface LocationService {

	public Location getLocation(int id);

	public Location createOrder(Location location);

	public Location updateOrder(Location location);

	public void deleteOrder(Location location);
}
