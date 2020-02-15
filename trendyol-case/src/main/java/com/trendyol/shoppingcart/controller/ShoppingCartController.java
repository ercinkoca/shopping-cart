package com.trendyol.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.shoppingcart.service.ShoppingService;

@RestController
@RequestMapping("/shopping")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingService service;
	
	@GetMapping("add-cart")
	public void addShoppingCart() {
		 service.addShoppingCart(); // Just for rest api sample.
	}

}
