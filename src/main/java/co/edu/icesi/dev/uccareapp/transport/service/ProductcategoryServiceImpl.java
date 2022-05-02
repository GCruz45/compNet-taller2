package co.edu.icesi.dev.uccareapp.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcategory;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductcategoryRepository;

@Service
public class ProductcategoryServiceImpl {
		
		private ProductcategoryRepository productCatRepo;

		@Autowired
		public ProductcategoryServiceImpl (
				ProductcategoryRepository productCatRepo)
		{
			this.productCatRepo = productCatRepo;
		}
		
		public Iterable<Productcategory> findAll() {
			return productCatRepo.findAll();
		}
	}
