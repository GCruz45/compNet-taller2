package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Location;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productinventory;
import co.edu.icesi.dev.uccareapp.transport.repositories.LocationRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductinventoryRepository;

@Service
public class ProductinventoryServiceImpl {
	
	private ProductinventoryRepository prodInvRepo;
	private ProductRepository productRepo;
	private LocationRepository locationRepo;

	public ProductinventoryServiceImpl (ProductinventoryRepository prodInvRepo, ProductRepository productRepo, LocationRepository locationRepo)
	{
		this.prodInvRepo = prodInvRepo;
		this.productRepo = productRepo;
		this.locationRepo = locationRepo;
	}

	public Productinventory addProdInv(Productinventory prodInv, int prodId, int locationId) throws Exception {

		Optional<Product> product = productRepo.findById(prodId);
		Optional<Location> location = locationRepo.findById(locationId);
		
		if(product.isEmpty())
			throw new Exception("Product does not exist");
		
		if(location.isEmpty())
			throw new Exception("Location does not exist");
		
		if (prodInv.getQuantity() < 0)
			throw new Exception("Product quantity cannot be negative");
		
		prodInv.setProduct(product.get());
		prodInv.setLocation(location.get());

		return prodInvRepo.save(prodInv);
	}

	public Productinventory editProdInv(Productinventory prodInv, int prodId, int locationId) throws Exception {
		
		Integer prodInvID = prodInv.getId();
		if(prodInvRepo.existsById(prodInvID)) {

			Optional<Product> product = productRepo.findById(prodId);
			Optional<Location> location = locationRepo.findById(locationId);
			
			if(product.isEmpty())
				throw new Exception("Product does not exist");
			
			if(location.isEmpty())
				throw new Exception("Location does not exist");
			
			if (prodInv.getQuantity() < 0)
				throw new Exception("Product quantity cannot be negative");
			
			prodInv.setProduct(product.get());
			prodInv.setLocation(location.get());

			return prodInvRepo.save(prodInv);
		} else throw new Exception("Product inventory does not exist on repo");
	}

}
