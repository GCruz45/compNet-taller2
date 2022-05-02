package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productsubcategory;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProdctsubcategoryRepository;

@Service
public class ProdctsubcategoryServiceImpl {
	
	private ProdctsubcategoryRepository productSubcatRepo;

	@Autowired
	public ProdctsubcategoryServiceImpl (
			ProdctsubcategoryRepository productSubcatRepo)
	{
		this.productSubcatRepo = productSubcatRepo;
	}
	
	public Iterable<Productsubcategory> findAll() {
		return productSubcatRepo.findAll();
	}

	public Optional<Productsubcategory> findById(int id) {

		return productSubcatRepo.findById(id);
	}
}
