package com.trendyol.shoppingcart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoppingCart {
	
	private List<Products> productList = new ArrayList<Products>();
	private Coupon coupon = new Coupon();
	private double deliveryCost = 0.0;
	
	public void addItem(Product product,int quantity) {
		Products products=new Products();
		products.setProduct(product);
		products.setQuantity(quantity);
		this.productList.add(products);
	}
	
	public void applyDiscounts(List<Campaign> campaignList) {
		
		HashMap<String, Campaign> campaignMap=new HashMap<String, Campaign>();
		for(Campaign c: campaignList) {
			campaignMap.put(c.getCategory().getTitle(), c);
		}
		// Eğer ürünün kategorisine tanımlı indirim varsa, o ürün bazında ürünün birim fiyatına kampanyadaki oranla indirim uyguluyor.
		// İsterlerden bu şekilde anladım.
		for(Products p: this.productList) {
			if(campaignMap.containsKey(p.getProduct().getCategory().getTitle())) {
				Campaign c=campaignMap.get(p.getProduct().getCategory().getTitle());
				if(c.getItemNumber() <= p.getQuantity()) {
					if(c.getDiscountType().equals(DiscountType.Amount)) {
						p.getProduct().setPrice(p.getProduct().getPrice() - c.getDiscount());
					}else {
						p.getProduct().setPrice(p.getProduct().getPrice() - ((p.getProduct().getPrice() * c.getDiscount() / 100 )));
					}
					p.getDiscount().setCampaign(c); // just for getCampaignDiscounts() method.
				}
			}
		}
		
		
	}
	
	public void applyCoupon(Coupon coupon) {
		int cartTotal = 0;
		for(Products p : this.productList) {
			cartTotal+=p.getQuantity() * p.getProduct().getPrice();
		}
		if(cartTotal > coupon.getMinPurchaseAmount()) { // rule of the coupon.
			this.coupon = coupon;
		}
	}
	
	public double getCouponDiscount() {
		double couponDiscount = 0.0;
		couponDiscount=this.coupon != null ? this.coupon.getDiscount() : 0.0;
		return couponDiscount;
	}
	
	public double getTotalAmountAfterDiscounts() {
		double totalAmountAfterDiscounts = 0.0;
		
		for(Products p: this.productList) {
			totalAmountAfterDiscounts += p.getQuantity() * p.getProduct().getPrice(); // total amounts before discounts.
		}
		
		// if there is a coupon and if this coupon obeys the rules, than totalAmount calculates again.
		if(this.coupon != null) {
			if(this.coupon.getDiscountType().equals(DiscountType.Rate)) {
				totalAmountAfterDiscounts -= totalAmountAfterDiscounts / this.coupon.getDiscount();
			}else {
				totalAmountAfterDiscounts -= this.coupon.getDiscount();
			}
		}
		return totalAmountAfterDiscounts;
	}
	
	public double getCampaignDiscounts() {
		double campaignDiscount = 0.0;
		for (Products p: this.productList) {
			if(p.getDiscount().getCampaign() != null) {
				if(p.getDiscount().getCampaign().getDiscount() > campaignDiscount) {
					campaignDiscount = p.getDiscount().getCampaign().getDiscount(); // Gets max discount
				}
			}
		}
		return campaignDiscount;
	}
	
	public double getDeliveryCost() {
		return this.deliveryCost;
	}
	
	public void print() {
		List<ShoppingCardResponse> list=new ArrayList<ShoppingCardResponse>();
		for(Products p : this.productList) {
			ShoppingCardResponse response=new ShoppingCardResponse();
			response.setProductName(p.getProduct().getTitle());
			response.setCategoryName(p.getProduct().getCategory().getTitle());
			response.setUnitPrice(p.getProduct().getPrice());
			response.setTotalPrice(p.getQuantity() * p.getProduct().getPrice());
			response.setTotalDiscount(p.getDiscount());
			response.setQuantity(p.getQuantity());
			list.add(response);
		}
		System.out.println("Shopping Card List ----");
		System.out.println(list);
		
	}
	

}
