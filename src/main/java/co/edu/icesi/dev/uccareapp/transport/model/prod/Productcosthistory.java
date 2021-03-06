package co.edu.icesi.dev.uccareapp.transport.model.prod;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import co.edu.icesi.dev.uccareapp.transport.model.system.markers.ProductcosthistoryValidation;


/**
 * The persistent class for the productcosthistory database table.
 *
 */
@Entity
@NamedQuery(name = "Productcosthistory.findAll", query = "SELECT p FROM Productcosthistory p")
public class Productcosthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTCOSTHISTORY_PRODUCTCOSTHISTORYID_GENERATOR", allocationSize=1, sequenceName="PRODUCTCOSTHISTORY_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTCOSTHISTORY_PRODUCTCOSTHISTORYID_GENERATOR")
	private Integer id;
	
	@PastOrPresent (groups = ProductcosthistoryValidation.class)
	private Timestamp enddate;

	private Timestamp modifieddate;

	private BigDecimal standardcost;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "productid", insertable = false, updatable = false)
	@NotNull (groups = ProductcosthistoryValidation.class)
	private Product product;

	public Productcosthistory() {
	}

	public Timestamp getEnddate() {
		return this.enddate;
	}

	public Integer getId() {
		return this.id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Product getProduct() {
		return this.product;
	}
    
	@PositiveOrZero (groups = ProductcosthistoryValidation.class)
	public BigDecimal getStandardcost() {
		return this.standardcost;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setStandardcost(BigDecimal standardcost) {
		this.standardcost = standardcost;
	}

}