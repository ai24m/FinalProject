package com.skilldistillery.lettucemeet.controllers;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.Type;
import com.skilldistillery.lettucemeet.services.ProductService;
import com.skilldistillery.lettucemeet.services.TypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class ProductController {

	@Autowired
	private ProductService prodSvc;
	
	@Autowired 
	private TypeService typeSvc; 

	@GetMapping("products")
	public List<Product> products() {
		return prodSvc.getProducts();
	}
	
	@GetMapping("types")
	public List<Type> types() {
		return typeSvc.getAllTypes();
	}

	@GetMapping("products/{id}")
	public Product showProduct(@PathVariable Integer id, HttpServletResponse res) {
		Product product = prodSvc.findById(id);
		if (product == null) {
			res.setStatus(404);
		}
		return product;
	}
	
	@GetMapping("products/user")
	public List<Product> products(Principal principal) {
		return prodSvc.getUserProducts(principal.getName());
	}

	@PostMapping("products")
	public Product createProduct(@RequestBody Product product, HttpServletResponse res, HttpServletRequest req,
			Principal principal) {
		try {
			prodSvc.createProduct(principal.getName(), product);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(product.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid entry for new product");
			res.setStatus(400);
			product = null;
		}
		return null;
	}

	@PutMapping("products/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product, HttpServletResponse res,
			Principal principal) {
		try {
			product = prodSvc.updateProduct(principal.getName(), id, product);
			if (product == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			product = null;
		}
		return product;
	}

	@DeleteMapping("products/{id}")
	public void deleteProduct(@PathVariable Integer id, HttpServletResponse res, Principal principal) {
		try {
			if (prodSvc.deleteProduct(principal.getName(), id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
			}
		});
		webDataBinder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("HH:mm").format((LocalTime) getValue());
			}
		});
		// 2020-11-04T09:44
		webDataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").format((LocalDateTime) getValue());
			}
		});
	}
	
	
	

}
