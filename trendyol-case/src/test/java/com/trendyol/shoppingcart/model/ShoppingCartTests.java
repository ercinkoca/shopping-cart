package com.trendyol.shoppingcart.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShoppingCartTests {
	
	private ShoppingCart cart = new ShoppingCart();
	private double result = 0.0;
	private double expectedResult = 0.0;
	
	@BeforeEach
	public void setup() {
		
		Category food=new Category("food");
		Category drinks = new Category("drinks");
		
		Product apple = new Product("Apple", 100.0, food);
		Product almond = new Product("Almonds", 150.0, food);
		Product beer = new Product("Beer", 50.0, drinks);
		
		
		cart.addItem(apple, 3);
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
		
	}
	
	@Test
	public void getTotalAmountAfterDiscounts() {
		result=cart.getTotalAmountAfterDiscounts();
		expectedResult=693.0;
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void getCouponDiscount() {
		result = cart.getCouponDiscount();
		expectedResult = 10.0;
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void getCampaignDiscounts() {
		result = cart.getCampaignDiscounts();
		expectedResult = 20.0;
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void getDeliveryCost() {
		result = cart.getDeliveryCost();
		expectedResult = 25.990000000000002;
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void cartPrint() {
		cart.print();
	}

}
