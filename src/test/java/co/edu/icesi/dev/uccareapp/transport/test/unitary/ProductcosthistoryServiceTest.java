package co.edu.icesi.dev.uccareapp.transport.test.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcosthistory;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductcosthistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.ProductcosthistoryServiceImpl;


@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductcosthistoryServiceTest {

	private Productcosthistory pch;

	@Mock
	private ProductcosthistoryRepository pchRepo;

	@Mock
	private ProductRepository productRepo;

	@InjectMocks
	private ProductcosthistoryServiceImpl pchService;

	public ProductcosthistoryServiceTest() {
	}

	@BeforeAll
	public void setUp() {
		pch = new Productcosthistory();
	}


	// la fecha de finalización no sea mayor a la actual y 
	// el costo estándar no sea negativo. 
	// Se debe validar que existan el producto (se pasa el identificador y se busca)

	@BeforeEach
	public void before(){
//		ProductcosthistoryPK pchPK = new ProductcosthistoryPK();
//		pchPK.setProductid(productID);
		int productID = 0;
		pch.setId(productID);
		
		pch.setEnddate(new Timestamp(100));
		pch.setStandardcost(new BigDecimal(100));
		Product product = new Product();
		product.setProductid(productID);
		pch.setProduct(product);
		
		Product product2 = new Product();
		product2.setProductid(productID + 10);

		when(pchRepo.save(pch)).thenReturn(pch);
		when(pchRepo.existsById(pch.getId())).thenReturn(true);
		when(pchRepo.findById(pch.getId())).thenReturn(Optional.of(pch));
		when(productRepo.findById(0)).thenReturn(Optional.of(product));
		when(productRepo.findById(1)).thenReturn(Optional.of(product2));
	}

	@Test
	@DisplayName("0: Add a product cost history (pch) with correct parameters")
	public void testAddPch0() {
		try {
			pchService.addPch(pch, 0);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("1: Add a pch with a null end date")
	public void testAddPch1() {
		pch.setEnddate(null);
		try {
			pchService.addPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("2: Add a pch with an end date that exceeds the current date")
	public void testAddPch2() {
		Timestamp newEndDate = new Timestamp(System.currentTimeMillis() + 50000);
		pch.setEnddate(newEndDate);
		try {
			pchService.addPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("3: Add a pch with a null standard cost")
	public void testAddPch3() {
		pch.setStandardcost(null);
		try {
			pchService.addPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("4: Add a pch with a negative standard cost")
	public void testAddPch4() {
		pch.setStandardcost(new BigDecimal(-1));
		try {
			pchService.addPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

//	@Test
//	@DisplayName("5: Add a pch with a null product")
//	public void testAddPch5() {
//		try {
//			pchService.addPch(pch, null);
//			fail();
//		} catch (Exception e) {
//			assertTrue(true);
//		}
//	}
//
//	@Test
//	@DisplayName("6: Add a pch with a product with no id")
//	public void testAddPch6() {
//		Product product = new Product();
//		product.setProductid(null);
//		pch.setProduct(product);
//		try {
//			pchService.addPch(pch, 0);
//			fail();
//		} catch (Exception e) {
//			assertTrue(true);
//		}
//	}

	@Test
	@DisplayName("7: Add a pch with a product whose id doesn't match the pch's id")
	public void testAddPch7() {
		try {
			pchService.addPch(pch, 1);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("100: Change the values of a pch with valid values")
	public void testEditPch0() {
		pch.setEnddate(new Timestamp(900));
		pch.setStandardcost(new BigDecimal(900));
		try {
			pchService.editPch(pch, 0);
			assertTrue(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("101: Change the product of a pch")
	public void testEditPch1() {
		pch.setProduct(new Product());
		try {
			pchService.editPch(pch, 0);
			assertTrue(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("102: Change the end date of a pch to null")
	public void testEditPch2() {
		pch.setEnddate(null);
		try {
			pchService.editPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("103: Change the end date of a pch to one that exceeds the current date")
	public void testEditPch3() {
		Timestamp newEndDate = new Timestamp(System.currentTimeMillis() + 50000);
		pch.setEnddate(newEndDate);
		try {
			pchService.editPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("104: Change the standard cost of a pch to null")
	public void testEditPch4() {
		pch.setStandardcost(null);
		try {
			pchService.editPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("105: Change the standard cost of a pch to a negative value")
	public void testEditPch5() {
		pch.setStandardcost(new BigDecimal(-1));
		try {
			pchService.editPch(pch, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	//	@AfterAll
	//	public void afterTest() {
	//		//		log.info("-----> DESTROY <-----");
	//	}
}
