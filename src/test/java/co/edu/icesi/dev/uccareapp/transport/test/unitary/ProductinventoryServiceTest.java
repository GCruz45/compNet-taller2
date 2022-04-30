package co.edu.icesi.dev.uccareapp.transport.test.unitary;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

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

import co.edu.icesi.dev.uccareapp.transport.model.prod.Location;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productinventory;
import co.edu.icesi.dev.uccareapp.transport.repositories.LocationRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.ProductinventoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.LocationServiceImpl;
import co.edu.icesi.dev.uccareapp.transport.service.ProductServiceImpl;
import co.edu.icesi.dev.uccareapp.transport.service.ProductinventoryServiceImpl;


@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductinventoryServiceTest {

	private Productinventory prodInv;

	@Mock
	private ProductinventoryRepository prodInvRepo;

	@Mock
	private ProductRepository productRepo;

	@Mock
	private LocationRepository locationRepo;

	@InjectMocks
	private ProductinventoryServiceImpl prodInvService;

	public ProductinventoryServiceTest() {
	}

	@BeforeAll
	public void setUp() {
		prodInv = new Productinventory();
	}

	// un inventario de producto asociado a una ubicación y un producto. 
	// Se debe validar que existan las dos llaves foráneas de guardarlo (se reciben los identificadores y se buscan). 
	// Se debe garantizar que la cantidad no sea inferior a cero.

	@BeforeEach
	public void before(){
		Location location = new Location();
		location.setLocationid(1);
		
		Product product = new Product();
		product.setProductid(1);
		
		prodInv.setQuantity(100);

		when(prodInvRepo.save(prodInv)).thenReturn(prodInv);
		when(productRepo.findById(0)).thenReturn(Optional.of(product));
		when(locationRepo.findById(0)).thenReturn(Optional.of(location));

		Location location2 = new Location();
		location2.setLocationid(2);

		Product product2 = new Product();
		product2.setProductid(2);
		
		when(productRepo.findById(1)).thenReturn(Optional.of(product2));
		when(locationRepo.findById(1)).thenReturn(Optional.of(location2));
		
		when(productRepo.findById(2)).thenReturn(Optional.empty());
		when(locationRepo.findById(2)).thenReturn(Optional.empty());
	}

	@Test
	@DisplayName("100: Add a product inventory with correct parameters")
	public void testAddProdInv0() {
		try {
			prodInvService.addProdInv(prodInv, 0, 0);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("101: Add a product inventory with a negative quantity")
	public void testAddProdInv1() {
		prodInv.setQuantity(-1);
		try {
			prodInvService.addProdInv(prodInv, 0, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("200: Change a product inventory's product and location with correct parameters")
	public void testEditProdInv0() {
		try {
			prodInvService.editProdInv(prodInv, 1, 1);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("201: Change a product inventory's product with an empty product")
	public void testEditProdInv1() {
		try {
			prodInvService.editProdInv(prodInv, 2, 0);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("202: Change a product inventory's location with an empty location")
	public void testEditProdInv2() {
		try {
			prodInvService.editProdInv(prodInv, 0, 2);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("203: Change a product inventory's quantity with a negative value")
	public void testEditProdInv3() {
		prodInv.setQuantity(-1);
		try {
			prodInvService.editProdInv(prodInv, 0, 2);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}


	// un inventario de producto asociado a una ubicación y un producto. 
	// Se debe validar que existan las dos llaves foráneas de guardarlo (se reciben los identificadores y se buscan). 
	// Se debe garantizar que la cantidad no sea inferior a cero.

	//	@AfterAll
	//	public void afterTest() {
	//		//		log.info("-----> DESTROY <-----");
	//	}
}
