package com.trendyol.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.DeliveryCostCalculator;
import com.trendyol.shoppingcart.model.DiscountType;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;
import com.trendyol.shoppingcart.service.ShoppingService;

@Service
public class ShoppingServiceImpl implements ShoppingService {
	
	
	public static void main(String[] args) {
		addShoppingCard();
	}
	
	public static void addShoppingCard() {
		Category food=new Category("food");
		Category drinks = new Category("drinks");
		
		Product apple = new Product("Apple", 100.0, food);
		Product almond = new Product("Almonds", 150.0, food);
		Product beer = new Product("Beer", 50.0, drinks);
		
		ShoppingCart cart = new ShoppingCart();
		
		cart.addItem(apple, 4);
		cart.addItem(almond, 1);
		cart.addItem(beer, 8);
		
		Campaign campaign1=new Campaign(food, 50.0, 5, DiscountType.Rate);
		Campaign campaign2=new Campaign(drinks, 20.0, 3, DiscountType.Rate);
		
		List<Campaign> campaignList = new ArrayList<Campaign>();
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		
		cart.applyDiscounts(campaignList);
		
		Coupon coupon = new Coupon(100, 10.0, DiscountType.Rate);
		cart.applyCoupon(coupon);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(10.0, 1.0);
		
		cart.setDeliveryCost(deliveryCostCalculator.calculateFor(cart));
		
		System.out.println("Delivery Cost --> " +cart.getDeliveryCost());
		System.out.println("Total Amount After Discounts -->" +cart.getTotalAmountAfterDiscounts());
		System.out.println("Coupon Discount --> "+cart.getCouponDiscount());
		System.out.println("Campaign Discount --> "+cart.getCampaignDiscounts());
		System.out.println("Delivery Cost --> " +cart.getDeliveryCost());
		cart.print();
		System.out.println("Total Amount --> "+cart.getTotalAmountAfterDiscounts());
		System.out.println("Delivery Cost ---> "+cart.getDeliveryCost());
		
	}

	@Override
	public Object addShoppingCart() {
		// Sample for controller. If we want, we can change this example to an api.
		return null;
	}

}
