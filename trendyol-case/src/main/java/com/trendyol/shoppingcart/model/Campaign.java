package com.trendyol.shoppingcart.model;

import lombok.Data;

@Data
public class Campaign {
	
	private Category category;
	private Double discount;
	private int itemNumber;
	private DiscountType discountType;
	
	public Campaign(Category category, Double discount, int itemNumber, DiscountType discountType) {
		super();
		
		this.category = category;
		this.discountType = discountType;
		this.itemNumber = itemNumber;
		
		if(itemNumber>=3 && itemNumber<5) {
			this.discount = new Double(20);
		}else if(itemNumber>=5) {
			this.discount= new Double(50);
		}else {
			this.discount= new Double(0);
		}
		
	}
	
	

}
