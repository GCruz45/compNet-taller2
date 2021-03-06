package co.edu.icesi.dev.uccareapp.transport.controller.implementation;

import java.sql.Timestamp;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.controller.interfaces.ProductController;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productsubcategory;
import co.edu.icesi.dev.uccareapp.transport.model.system.Users;
import co.edu.icesi.dev.uccareapp.transport.model.system.markers.ProductValidation;
import co.edu.icesi.dev.uccareapp.transport.service.ProdctsubcategoryServiceImpl;
import co.edu.icesi.dev.uccareapp.transport.service.ProductServiceImpl;
import co.edu.icesi.dev.uccareapp.transport.service.ProductcategoryServiceImpl;
import co.edu.icesi.dev.uccareapp.transport.service.UserServiceImpl;

@Controller
public class ProductControllerImpl implements ProductController {

	ProductServiceImpl prodService;
	ProdctsubcategoryServiceImpl prodSubCatService;
	ProductcategoryServiceImpl prodCatService;

	@Autowired
	public ProductControllerImpl(	ProductServiceImpl prodService, 
									ProdctsubcategoryServiceImpl prodSubCatService, 
									ProductcategoryServiceImpl prodCatService) {
		this.prodService = prodService;
		this.prodSubCatService = prodSubCatService;
		this.prodCatService = prodCatService;
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/prods/")
	public String indexProd(Model model) {
		model.addAttribute("prods", prodService.findAll());
		return "prods/prod-index";
	}

	@GetMapping("/prods/add")
	public String addProd(Model model) {
		model.addAttribute("prods", new Product());
		model.addAttribute("subCats", prodSubCatService.findAll());
		model.addAttribute("categories", prodCatService.findAll());
//	    model.addAttribute("ts", new Timestamp());
		return "prods/prod-add";
	}

	@PostMapping("/prods/add")
	public String saveProd(@Validated(ProductValidation.class) @ModelAttribute("prods")  Product product,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) throws Exception {
		if (action.equals("Cancel"))
			return "redirect:/prods/";
		if (action.equals("Add Product")) {
			model.addAttribute("id", product.getProductid());
			model.addAttribute("prods", product);
			model.addAttribute("subCats", prodSubCatService.findAll());
			model.addAttribute("categories", prodCatService.findAll());
			
			int subcatId = product.getProductsubcategory().getProductsubcategoryid();
			int catId = product.getProductsubcategory().getProductcategory().getProductcategoryid();
			
			prodService.addProduct(product,catId,subcatId);
			return "redirect:/prods/";
		}
		if (bindingResult.hasErrors()) {
			return "prods/prod-add";
		}
		return "prods/prod-add";
	}

	@GetMapping("/prods/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Optional<Product> product = prodService.findById(id);
		if (product == null)
			throw new IllegalArgumentException("Invalid product Id:" + id);
		model.addAttribute("prod", product.get());
//		model.addAttribute("genders", userService.getGenders());
//		model.addAttribute("types", userService.getTypes());
		return "prods/prod-edit";
	}

	@PostMapping("/prods/edit/{id}")
	public String updateProd(@PathVariable("id") int id,
			@RequestParam(value = "action", required = true) String action,
			@Validated(ProductValidation.class) @ModelAttribute Product product, BindingResult bindingResult, Model model) throws Exception {

		if (action.equals("Cancel")) {
			return ("redirect:/prods/");
		}

//		if (!user.getOldPassword().equals(user.getPassword()))
//			// https://docs.spring.io/spring-framework/docs/3.1.x/javadoc-api/org/springframework/validation/Errors.html#rejectValue%28java.lang.String,%20java.lang.String,%20java.lang.String%29
//			bindingResult.rejectValue("oldPassword", "oldPassword.error");

		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
//				model.addAttribute("userApp",user);
////				model.addAttribute("genders", userService.getGenders());
//				model.addAttribute("types", userService.getTypes());
				return "prods/prod-edit";
			}
			prodService.addProduct(product,0,0);
			model.addAttribute("prod", prodService.findAll());
		}
		return "redirect:/prods/";
	}

	@GetMapping("/prods/del/{id}")
	public String deleteProd(@PathVariable("id") int id, Model model) {
		Product product = prodService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
		prodService.delete(product);
		model.addAttribute("prod", prodService.findAll());
		return "prods/prod-index";
	}
}
