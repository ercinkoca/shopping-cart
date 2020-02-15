package com.trendyol.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
	
	private int quantity;
	private Product product;
	private Discount discount = new Discount();

}
