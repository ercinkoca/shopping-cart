package com.trendyol.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCardResponse {
	
	private String productName;
	private String categoryName;
	private int quantity;
	private double unitPrice;
	private double totalPrice;
	private Discount totalDiscount;

}
