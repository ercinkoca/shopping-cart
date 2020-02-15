package com.trendyol.shoppingcart.model;

import lombok.Data;

@Data
public class Coupon {
	
	private int minPurchaseAmount;
	private Double discount;
	private DiscountType discountType;
	
	public Coupon(int minPurchaseAmount, Double discount, DiscountType discountType) {
		super();
		
		this.minPurchaseAmount = minPurchaseAmount;
		this.discountType = discountType;
		if(minPurchaseAmount >= 100) {
			this.discount = new Double(10);
		}else {
			this.discount = new Double(0);
		}
		
	}
	
	public Coupon() {
	}
	
	

}
