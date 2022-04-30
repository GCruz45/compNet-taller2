package co.edu.icesi.dev.uccareapp.transport.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcategory;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productsubcategory;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProdctsubcategoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductcategoryRepository;


@Service
public class ProductServiceImpl {
	private ProductRepository productRepo;
	private ProductcategoryRepository productCatRepo;
	private ProdctsubcategoryRepository productSubcatRepo;

	@Autowired
	public ProductServiceImpl (ProductRepository productRepo, 
			ProductcategoryRepository productCatRepo,
			ProdctsubcategoryRepository productSubcatRepo)
	{
		this.productRepo = productRepo;
		this.productCatRepo = productCatRepo;
		this.productSubcatRepo = productSubcatRepo;
	}

	public Product addProduct(Product product, int productCatId, int productSubcatId) throws Exception {
		
		Productcategory productCategory = productCatRepo.findById(productCatId).get();
		Productsubcategory productSubcategory = productSubcatRepo.findById(productSubcatId).get();
		
		if (product.getProductid() == null)
			throw new Exception("Product has no id");

		if (productSubcategory == null)
			throw new Exception("Product must have a subcategory");

		if (productSubcategory.getProductcategory() == null)
			throw new Exception("Product's subcategory must belong to a category");

		if (product.getProductnumber() == null || product.getProductnumber().length()==0)
			throw new Exception("Product number cannot be empty");

		if (product.getSellstartdate() == null)
			throw new Exception("Sell start date cannot be empty");

		if (product.getSellenddate() == null)
			throw new Exception("Sell end date cannot be empty");

		if (product.getSellstartdate().compareTo(product.getSellenddate()) > 0)
			throw new Exception("Sell start date cannot be greater than sell end date");

		if (product.getSize() < 0)
			throw new Exception("Product size can't be negative");

		if (product.getWeight() < 0)
			throw new Exception("Product weight can't be negative");
		
		productSubcategory.setProductcategory(productCategory);
		product.setProductsubcategory(productSubcategory);

		return productRepo.save(product);
	}

	public Product editProduct(Product product, int productCatId, int productSubcatId) throws Exception {
		
		Productcategory productCategory = productCatRepo.findById(productCatId).get();
		Productsubcategory productSubcategory = productSubcatRepo.findById(productSubcatId).get();
		
		if(productRepo.existsById(product.getProductid())) {

			if (productSubcategory.getProductcategory() == null)
				throw new Exception("Product's subcategory must belong to a category");

			if (product.getProductnumber() == null || product.getProductnumber().length()==0)
				throw new Exception("Product number cannot be empty");

			if (product.getSellstartdate().compareTo(product.getSellenddate()) > 0)
				throw new Exception("Sell start date cannot be greater than sell end date");

			if (product.getSellstartdate() == null)
				throw new Exception("Sell start date cannot be empty");

			if (product.getSellenddate().compareTo(product.getSellstartdate()) < 0)
				throw new Exception("Sell end date cannot be lesser than sell start date");

			if (product.getSellstartdate() == null)
				throw new Exception("Sell end date cannot be empty");

			if (product.getSize() < 0)
				throw new Exception("Product size can't be negative");

			if (product.getWeight() < 0)
				throw new Exception("Product weight can't be negative");
			
			productSubcategory.setProductcategory(productCategory);
			product.setProductsubcategory(productSubcategory);
			
			return productRepo.save(product);
		}
		else throw new Exception("Product does not exist on repo");

	}
	
	// Comienzan metodos propios del taller 2:
	
	public Iterable<Product> findAll() {
		return productRepo.findAll();
	}
	

	public Optional<Product> findById(int id) {

		return productRepo.findById(id);
	}
	
	public void delete(Product product) {
		productRepo.delete(product);
	}
}
