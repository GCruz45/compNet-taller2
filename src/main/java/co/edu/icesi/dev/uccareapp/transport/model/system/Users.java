package co.edu.icesi.dev.uccareapp.transport.model.system;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import co.edu.icesi.dev.uccareapp.transport.model.system.markers.ProductValidation;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Size(min=3, message = "Nombre de usuario debe ser de minimo tres letras", groups = {ProductValidation.class})
	@NotBlank (groups = {ProductValidation.class})
	private String username;

	@Size(min=8, message = "Contraseña debe ser de minimo 8 letras", groups = ProductValidation.class)
	@NotBlank (groups = ProductValidation.class)
	private String password;
	
	@NotNull (groups = ProductValidation.class)
	private UserType type;

	@Transient
	@Size(min=8, message = "La contraseña nueva debe ser de minimo 8 letras", groups = ProductValidation.class)
	@NotBlank (groups = ProductValidation.class)
	private String newPassword;

	@Transient
	@NotBlank (groups = ProductValidation.class)
	@Size(min=8, message = "Contrasena debe ser de minimo 8 letras", groups = ProductValidation.class)
	private String oldPassword;
	
//	@Past (groups = {StepOneUserValidation.class,EditValidation.class})
//	@NotNull (groups = {StepOneUserValidation.class,EditValidation.class})
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate birthDate;
	
//	@Email (groups = {StepTwoUserValidation.class,EditValidation.class})
//	@NotBlank (groups = {StepTwoUserValidation.class,EditValidation.class})
//	private String email;

//	@Size(min=2, message = "Nombre debe ser de minimo dos letras", groups = {StepTwoUserValidation.class,EditValidation.class})
//	@NotBlank (groups = {StepTwoUserValidation.class,EditValidation.class})
//	private String name;


//	@NotNull (groups = {StepTwoUserValidation.class,EditValidation.class})
//	private UserGender gender;

	//	@OneToMany
	//	private List<Appointment> appointments;
}
