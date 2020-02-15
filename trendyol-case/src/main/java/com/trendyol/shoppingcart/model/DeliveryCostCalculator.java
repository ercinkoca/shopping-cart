package com.trendyol.shoppingcart.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCostCalculator {

	private double costPerDelivery;
	private double costPerProduct;
	private final double fixedCost = 2.99; //default value.
	
	public double calculateFor(ShoppingCart cart) {
		double costForCart = 0.0;
		List<Products> products=cart.getProductList();
		int numberOfDeliveries = 0;
		int numberOfProducts = 0; // bu kartta kaç farklı ürün var. onu bulmam lazım.
		Set<String> categoriesSet=new HashSet<String>();
		Set<String> numberOfProdcutsSet=new HashSet<String>();
		
		for(Products p:products) {
			categoriesSet.add(p.getProduct().getCategory().getTitle()); 
			numberOfProdcutsSet.add(p.getProduct().getTitle());
		}
		
		numberOfDeliveries=categoriesSet.size(); //Number of Deliveries is calculated by number of distinct categories in that cart.
		numberOfProducts = numberOfProdcutsSet.size(); // Number of products is calculated by how many different products in cart.
		costForCart = (this.costPerDelivery * numberOfDeliveries) + (this.costPerProduct * numberOfProducts) + this.fixedCost;
		return costForCart;
	}
}
