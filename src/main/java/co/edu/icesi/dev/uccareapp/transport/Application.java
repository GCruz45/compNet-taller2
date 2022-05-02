package co.edu.icesi.dev.uccareapp.transport;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcategory;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productsubcategory;
import co.edu.icesi.dev.uccareapp.transport.model.system.UserType;
import co.edu.icesi.dev.uccareapp.transport.model.system.Users;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProdctsubcategoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductcategoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;

@SpringBootApplication
//@ComponentScan(basePackages = {"main"})
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductcategoryRepository prodCatRepo;
	@Autowired
	private ProdctsubcategoryRepository prodSubcatRepo;
	@Autowired
	private ProductRepository prodRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {
    	Users newUser = new Users();
    	newUser.setUsername("adamNoop");
    	newUser.setPassword("{noop}12341234");
    	newUser.setType(UserType.ADMIN);;
    	
    	userRepo.save(newUser);
    	
    	Users newUser2 = new Users();
    	newUser2.setUsername("abelNoop");
    	newUser2.setPassword("{noop}12341234");
    	newUser2.setType(UserType.operator);;
    	
    	userRepo.save(newUser2);
    	
    	// Create & Save product_1: requires a cat and a subcat to be saved to the db first
    	// Must have: number, cat, subcat, sellstart, sellend, size, weight
    	
    	// First set of category/subcategory
		Productcategory prodCat = new Productcategory();
		prodCat.setName("cat_1");
		prodCatRepo.save(prodCat);
		
		Productsubcategory prodSubcat = new Productsubcategory();
		prodSubcat.setName("subCat_1");
		prodSubcat.setProductcategory(prodCat);
		prodSubcatRepo.save(prodSubcat);
		

    	// Second set of category/subcategory
		Productcategory prodCat2 = new Productcategory();
		prodCat2.setName("cat_2");
		prodCatRepo.save(prodCat2);
		
		Productsubcategory prodSubcat2 = new Productsubcategory();
		prodSubcat2.setName("subCat_2");
		prodSubcat2.setProductcategory(prodCat2);
		prodSubcatRepo.save(prodSubcat2);
		
    	Product product = new Product();
//		product.setProductid(0);
		
		product.setProductsubcategory(prodSubcat);
		product.setProductnumber("productNumber");
		product.setSellstartdate(new Date(0));
		product.setSellenddate(new Date(1));
		product.setSize(5);
		product.setWeight(10);
		
		prodRepo.save(product);
		
    }
}
