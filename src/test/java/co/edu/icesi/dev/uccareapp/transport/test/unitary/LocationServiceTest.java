package co.edu.icesi.dev.uccareapp.transport.test.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productcategory;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productsubcategory;
import co.edu.icesi.dev.uccareapp.transport.repositories.LocationRepository;
import co.edu.icesi.dev.uccareapp.transport.service.LocationServiceImpl;
import co.edu.icesi.dev.uccareapp.transport.service.ProductServiceImpl;


@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LocationServiceTest {

	private Location location;

	@Mock
	private LocationRepository locationRepo;

	@InjectMocks
	private LocationServiceImpl locationService;

	public LocationServiceTest() {
	}

	@BeforeAll
	public void setUp() {
		location = new Location();
	}

	@BeforeEach
	public void before(){
		location.setName("12345");
		location.setAvailability(new BigDecimal(7));
		location.setCostrate(new BigDecimal(0.5));

		when(locationRepo.save(location)).thenReturn(location);
		when(locationRepo.existsById(location.getLocationid())).thenReturn(true);
	}

	@Test
	@DisplayName("0: Add location with correct parameters")
	public void testAddLocation0() {
		try {			
			locationService.addLocation(location);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("1: Add location with a null name")
	public void testAddLocation1() {
		location.setName(null);
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("2: Add location with a name whose length is lesser than 5")
	public void testAddLocation2() {
		location.setName("1234");
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("3: Add location with a null availability")
	public void testAddLocation3() {
		location.setAvailability(null);
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("4: Add location with an availability lesser than 1")
	public void testAddLocation4() {
		location.setAvailability(new BigDecimal(-0.5));
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("5: Add location with an availability greater than 10")
	public void testAddLocation5() {
		location.setAvailability(new BigDecimal(10.5));
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("6: Add location with a null cost rate")
	public void testAddLocation6() {
		location.setCostrate(null);
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("7: Add location with a cost rate lesser than 0")
	public void testAddLocation7() {
		location.setCostrate(new BigDecimal(-0.5));
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("8: Add location with a cost rate greater than 1")
	public void testAddLocation8() {
		location.setCostrate(new BigDecimal(1.1));
		try {			
			locationService.addLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("100: Change a location's fields with new, valid ones")
	public void testEditLocation0() {
		Location oldLocation = location;
		location.setName("123456");
		location.setAvailability(new BigDecimal(9));
		location.setCostrate(new BigDecimal(0.9));
		try {			
			Location newLocation = locationService.editLocation(location);
			assertEquals(oldLocation.getLocationid(), newLocation.getLocationid());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("101: Change a location's name with a valid value")
	public void testEditLocation1() {
		String newName = "1234567";
		location.setName(newName);
		try {			
			Location newLocation = locationService.editLocation(location);
			assertEquals(newName, newLocation.getName());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("102: Change a location's name with a null one")
	public void testEditLocation2() {
		location.setName(null);
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("103: Change a location's name with one whose length is lesser than 5")
	public void testEditLocation3() {
		String newName = "123";
		location.setName(newName);
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("104: Change a location's availability with a valid value")
	public void testEditLocation4() {
		BigDecimal newAvailability = new BigDecimal(9);
		location.setAvailability(newAvailability);
		try {			
			Location newLocation = locationService.editLocation(location);
			assertTrue(newAvailability.equals(newLocation.getAvailability()));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("105: Change a location's availability with a null one")
	public void testEditLocation5() {
		location.setAvailability(null);
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("106: Change a location's availability with one that's lesser than 1")
	public void testEditLocation6() {
		location.setAvailability(new BigDecimal(-0.4));
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("107: Change a location's availability with one that's greater than 10")
	public void testEditLocation7() {
		location.setAvailability(new BigDecimal(10.5));
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("108: Change a location's cost rate with a valid value")
	public void testEditLocation8() {
		BigDecimal newCostRate = new BigDecimal(0.9);
		location.setCostrate(newCostRate);
		try {			
			Location newLocation = locationService.editLocation(location);
			assertTrue(newCostRate.equals(newLocation.getCostrate()));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@DisplayName("109: Change a location's cost rate with a null one")
	public void testEditLocation9() {
		location.setCostrate(null);
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("110: Change a location's cost rate with one that's lesser than 0")
	public void testEditLocation10() {
		location.setCostrate(new BigDecimal(-0.1));
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("111: Change a location's cost rate with one that's greater than 1")
	public void testEditLocation11() {
		location.setCostrate(new BigDecimal(1.1));
		try {			
			locationService.editLocation(location);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}


	// el nombre tenga al menos cinco caracteres,
	// la disponibilidad esté entre 1 y 10 y
	// la tasa de costo entre 0 y 1.


	//	@AfterAll
	//	public void afterTest() {
	//		//		log.info("-----> DESTROY <-----");
	//	}
}
