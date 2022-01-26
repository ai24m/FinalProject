package com.skilldistillery.lettucemeet.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.services.ProductService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class ProductController {
	
	@Autowired
	private ProductService prodSvc;
	
	@GetMapping("products")
	public List<Product> products() {
		return prodSvc.getProducts();
	}
	
	@GetMapping("products/{id}")
	public Product showProduct(@PathVariable Integer id, HttpServletResponse res) {
		Product product = prodSvc.findById(id);
		if (product == null) {
			res.setStatus(404);
		}
		return product;
	}
	
	@PostMapping("products")
	public Product createProduct(@RequestBody Product product, HttpServletResponse res, HttpServletRequest req) {
		try {
			prodSvc.createProduct(product);
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
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product, HttpServletResponse res) {
		try {
			product = prodSvc.updateProduct(id, product);
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
	public void deleteProduct(@PathVariable Integer id, HttpServletResponse res) {
		try {
			if (prodSvc.deleteProduct(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
