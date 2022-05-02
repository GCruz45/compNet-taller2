package co.edu.icesi.dev.uccareapp.transport.model.prod;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import co.edu.icesi.dev.uccareapp.transport.model.system.markers.LocationValidation;

/**
 * The persistent class for the location database table.
 *
 */
@Entity
@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LOCATION_LOCATIONID_GENERATOR", allocationSize = 1, sequenceName = "LOCATION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_LOCATIONID_GENERATOR")
	private Integer locationid;

	@Size(min=1, max=10, message = "Location availability must fall within 1-10 range.", groups = LocationValidation.class)
	private BigDecimal availability;

	@Size(min=0, max=1, message = "Location cost rate must fall within 0-1 range.", groups = LocationValidation.class)
	private BigDecimal costrate;

	private Timestamp modifieddate;
	
	@Size(min=5, message = "Location name's min size must be 5.", groups = LocationValidation.class)
	private String name;

	// bi-directional many-to-one association to Productinventory
	@OneToMany(mappedBy = "location")
	private List<Productinventory> productinventories;

	// bi-directional many-to-one association to Workorderrouting
	@OneToMany(mappedBy = "location")
	private List<Workorderrouting> workorderroutings;

	public Location() {
	}

	public Productinventory addProductinventory(Productinventory productinventory) {
		getProductinventories().add(productinventory);
		productinventory.setLocation(this);

		return productinventory;
	}

	public Workorderrouting addWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().add(workorderrouting);
		workorderrouting.setLocation(this);

		return workorderrouting;
	}

	public BigDecimal getAvailability() {
		return this.availability;
	}

	public BigDecimal getCostrate() {
		return this.costrate;
	}

	public Integer getLocationid() {
		return this.locationid;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public List<Productinventory> getProductinventories() {
		return this.productinventories;
	}

	public List<Workorderrouting> getWorkorderroutings() {
		return this.workorderroutings;
	}

	public Productinventory removeProductinventory(Productinventory productinventory) {
		getProductinventories().remove(productinventory);
		productinventory.setLocation(null);

		return productinventory;
	}

	public Workorderrouting removeWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().remove(workorderrouting);
		workorderrouting.setLocation(null);

		return workorderrouting;
	}

	public void setAvailability(BigDecimal availability) {
		this.availability = availability;
	}

	public void setCostrate(BigDecimal costrate) {
		this.costrate = costrate;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductinventories(List<Productinventory> productinventories) {
		this.productinventories = productinventories;
	}

	public void setWorkorderroutings(List<Workorderrouting> workorderroutings) {
		this.workorderroutings = workorderroutings;
	}

}