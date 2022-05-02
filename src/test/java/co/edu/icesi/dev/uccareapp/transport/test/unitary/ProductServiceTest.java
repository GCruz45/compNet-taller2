package co.edu.icesi.dev.uccareapp.transport.test.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcategory;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productsubcategory;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProdctsubcategoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductcategoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.ProductServiceImpl;


@SpringBootTest()
//@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = Taller1A00056020Application.class)
//@SpringBootTest(classes= Taller1A00056020Application.class)
class ProductServiceTest {

	private Product product;

	@Mock
	private ProductRepository productRepo;

	@Mock
	private ProductcategoryRepository productCatRepo;

	@Mock
	private ProdctsubcategoryRepository productSubcatRepo;

	@InjectMocks
	private ProductServiceImpl productService;

	public ProductServiceTest() {
	}

	@BeforeAll
	public void setUp() {
		product = new Product();
	}

	@BeforeEach
	public void before(){
		product.setProductid(0);
		Productcategory prodCat = new Productcategory();
		Productsubcategory prodSubcat = new Productsubcategory();
		prodSubcat.setProductcategory(prodCat);
		
		product.setProductsubcategory(prodSubcat);
		product.setProductnumber("productNumber");
		product.setSellstartdate(new Date(0));
		product.setSellenddate(new Date(1));
		product.setSize(5);
		product.setWeight(10);
		

		Productcategory prodCat2 = new Productcategory();
		
		when(productRepo.save(product)).thenReturn(product);
		when(productRepo.existsById(product.getProductid())).thenReturn(true);
		when(productCatRepo.findById(0)).thenReturn(Optional.of(prodCat));
		when(productCatRepo.findById(1)).thenReturn(Optional.of(prodCat2));
		when(productSubcatRepo.findById(0)).thenReturn(Optional.of(prodSubcat));
	}

	// guardar un producto escogiendo la categoría y subcategoría (debe
	// previamente crearlas y poder asociarlas al producto) 
	// garantizando que tenga 
	// un número de producto, 
	// una fecha de inicio de venta menor a la fecha de fin, 
	// y tamaño y peso mayores a cero.


	@Test
	@DisplayName("0: Add product with correct parameters")
	public void testAddProduct0() {
		try {			
			productService.addProduct(product, 0, 0);
			assertTrue(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

//	@Test
//	@DisplayName("1: Add product without a subcategory")
//	public void testAddProduct1() {
//		product.setProductsubcategory(null);
//		try {			
//			productService.addProduct(product, 0, 0);
//			fail();
//		} catch (Exception e) {
//			assertTrue(true);
//		}
//	}
//
	@Test
	@DisplayName("2: Add product whose subcategory's category doesn't match with the expected category")
	public void testAddProduct2() {
		try {			
			productService.addProduct(product, 0, 1);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("3: Add product without a starting sell date")
	public void testAddProduct3() {
		product.setSellstartdate(null);
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("4: Add product without an ending sell date")
	public void testAddProduct4() {
		product.setSellenddate(null);
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("5: Add product whose starting sell date is greater than its ending sell date")
	public void testAddProduct5() {
		product.setSellstartdate(new Date(1));
		product.setSellenddate(new Date(0));
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("6: Add product whose size is negative")
	public void testAddProduct6() {
		product.setSize(-1);
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("7: Add product whose weight is negative")
	public void testAddProduct7() {
		product.setWeight(-1);
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("8: Add product whose product number is null")
	public void testAddProduct8() {
		product.setProductnumber(null);
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("9: Add product whose product number is empty")
	public void testAddProduct9() {
		product.setProductnumber("");
		try {			
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("10: Add product whose id is null")
	public void testAddProduct10() {
		product.setProductid(null);
		try {
			productService.addProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

//	@Test
//	@DisplayName("100: Change a product's subcategory with a new, valid one")
//	public void testEditProduct0() {
//		Productsubcategory prodSubcat = new Productsubcategory();
//		prodSubcat.setProductcategory(new Productcategory());
//		prodSubcat.setName("cat2");
//		product.setProductsubcategory(prodSubcat);
//		try {			
//			Product updatedProduct = productService.editProduct(product, 0, 0);
//			assertTrue(updatedProduct.getProductsubcategory().getName().equals("cat2"));
//		} catch (Exception e) {
//			fail();
//		}
//	}
//
//	@Test
//	@DisplayName("101: Change a product's subcategory with one that doesn't have a category")
//	public void testEditProduct1() {
//		Productsubcategory prodSubcat = new Productsubcategory();
//		product.setProductsubcategory(prodSubcat);
//		try {			
//			productService.editProduct(product, 0, 0);
//			fail();
//		} catch (Exception e) {
//			assertTrue(true);
//		}
//	}

	@Test
	@DisplayName("102: Change a product's product number with null")
	public void testEditProduct2() {
		product.setProductnumber(null);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("103: Change a product's product number with empty")
	public void testEditProduct3() {
		product.setProductnumber("");
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("104: Change a product's sell start date with a valid one")
	public void testEditProduct4() {
		Date newSellStartDate = new Date(1);
		product.setSellstartdate(newSellStartDate);
		try {			
			Product updatedProduct = productService.editProduct(product, 0, 0);
			assertTrue(updatedProduct.getSellstartdate().equals(newSellStartDate));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("105: Change a product's sell start date with one that exceeds the sell end date")
	public void testEditProduct5() {
		Date newSellStartDate = new Date(2);
		product.setSellstartdate(newSellStartDate);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("106: Change a product's sell start date with null")
	public void testEditProduct6() {
		product.setSellstartdate(null);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("107: Change a product's sell end date with a valid one")
	public void testEditProduct7() {
		Date newSellEndDate = new Date(10);
		product.setSellenddate(newSellEndDate);
		try {			
			Product updatedProduct = productService.editProduct(product, 0, 0);
			assertTrue(updatedProduct.getSellenddate().equals(newSellEndDate));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("108: Change a product's sell end date with one that undercuts the sell start date")
	public void testEditProduct8() {
		Date newSellEndDate = new Date(0);
		product.setSellstartdate(new Date(1));
		product.setSellenddate(newSellEndDate);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("109: Change a product's sell end date with null")
	public void testEditProduct9() {
		product.setSellenddate(null);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	@DisplayName("110: Change a product's size with a valid value")
	public void testEditProduct10() {
		long newSize = 5;
		product.setSize(newSize);
		try {			
			Product updatedProduct = productService.editProduct(product, 0, 0);
			assertEquals(updatedProduct.getSize(), newSize);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("111: Change a product's size with a negative value")
	public void testEditProduct11() {
		product.setSize(-1);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("112: Change a product's weight with a valid value")
	public void testEditProduct12() {
		long newWeight = 5;
		product.setWeight(newWeight);
		try {			
			Product updatedProduct = productService.editProduct(product, 0, 0);
			assertEquals(updatedProduct.getWeight(), newWeight);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("113: Change a product's weight with a negative value")
	public void testEditProduct13() {
		product.setWeight(-1);
		try {			
			productService.editProduct(product, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("114: Change a product's fields with new, valid ones")
	public void testEditProduct14() {
		int oldProductId = product.getProductid();
		
		product.setProductnumber("newProductNumber");
		product.setSellstartdate(new Date(10));
		product.setSellenddate(new Date(11));
		product.setSize(50);
		product.setWeight(100);
		
		try {			
			Product newProduct = productService.editProduct(product, 0, 0);
			assertEquals(oldProductId, newProduct.getProductid());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}	

	// guardar un producto escogiendo la categoría y subcategoría (debe
	// previamente crearlas y poder asociarlas al producto) 
	// garantizando que tenga 
	// un número de producto, 
	// una fecha de inicio de venta menor a la fecha de fin, 
	// y tamaño y peso mayores a cero.


	//	@AfterAll
	//	public void afterTest() {
	//		//		log.info("-----> DESTROY <-----");
	//	}
}
