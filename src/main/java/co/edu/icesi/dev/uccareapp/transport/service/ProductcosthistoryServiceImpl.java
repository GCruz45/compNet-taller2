package co.edu.icesi.dev.uccareapp.transport.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcosthistory;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductcosthistoryRepository;

@Service
public class ProductcosthistoryServiceImpl {

	private ProductcosthistoryRepository pchRepo;
	
	private ProductRepository prodRepo;

	public ProductcosthistoryServiceImpl (ProductcosthistoryRepository pchRepo, ProductRepository prodRepo)
	{
		this.pchRepo = pchRepo;
		this.prodRepo = prodRepo;
	}

	public Productcosthistory addPch(Productcosthistory pch, int prodId) throws Exception {
		
		Optional<Product> product = prodRepo.findById(prodId);
		
		if (product.isEmpty())
			throw new Exception("Pch's product cannot be null");

		if (product.get().getProductid() == null)
			throw new Exception("Pch's product cannot have a null id");

		if (product.get().getProductid() != pch.getId())
			throw new Exception("Pch's product id doesn't match pch's id");

		if (pch.getEnddate() == null)
			throw new Exception("End date cannot be null");

		if (pch.getEnddate().getTime() > System.currentTimeMillis())
			throw new Exception("End date cannot exceed the current time");

		if (pch.getStandardcost() == null)
			throw new Exception("Standard cost cannot be null");

		if (pch.getStandardcost().compareTo(new BigDecimal(0)) < 0)
			throw new Exception("Standard cost cannot be negative");
		
		pch.setProduct(product.get());

		return pchRepo.save(pch);
	}

	public Productcosthistory editPch(Productcosthistory pch, int prodId) throws Exception {

		Optional<Product> product = prodRepo.findById(prodId);
		
		Integer pchID = pch.getId();
		if(pchRepo.existsById(pchID)) {
			Product productThatExistsInRepo = pchRepo.findById(pchID).get().getProduct();
			
			if (!pch.getProduct().equals(productThatExistsInRepo)) 
				throw new Exception("Product of given pch does not match product of pch in repo");

			if (pch.getEnddate() == null)
				throw new Exception("End date cannot be null");

			if (pch.getEnddate().getTime() > System.currentTimeMillis())
				throw new Exception("End date cannot exceed the current time");

			if (pch.getStandardcost() == null)
				throw new Exception("Standard cost cannot be null");

			if (pch.getStandardcost().compareTo(new BigDecimal(0)) < 0)
				throw new Exception("Standard cost cannot be negative");

			pch.setProduct(product.get());
			
			return pchRepo.save(pch);
		} else throw new Exception("Pch does not exist on repo");
	}
}
