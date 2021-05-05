package com.coffeeAssessment.main;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coffeeAssessment.entities.Order;
import com.coffeeAssessment.entities.Payment;
import com.coffeeAssessment.entities.Product;

public class Amount {

	private JsonJavaMapper jsonJavaMapper;
	private List<Order> orders;
	private List<Payment> payments;
	private List<Product> products;

	Amount() {
		jsonJavaMapper = new JsonJavaMapper();
		jsonJavaMapper.readJson();
		orders = jsonJavaMapper.getOrders();
		payments = jsonJavaMapper.getPayments();
		products = jsonJavaMapper.getProducts();
	}

	/**
	 * Calculate the total amount to be paid per user and display all other fields
	 */
	public void calculateAmountPerUser() {
		System.out.format("%12s%25s%20s%20s%20s", "USER |", " TOTAL AMOUNT TO BE PAID |", " AMOUNT ALREADY PAID |",
				" AMOUNT OWED BY USER |", " EXCESS AMOUNT PAID |").println();
		System.out.println();
		// Group orders by user
		Map<String, List<Order>> ordersPerUser = orders.stream().collect(Collectors.groupingBy(Order::getUser));
		for (Map.Entry<String, List<Order>> entry : ordersPerUser.entrySet()) {
			String user = entry.getKey();
			Double totalPrice = entry.getValue().stream().mapToDouble(this::getPriceForOrder).sum();
			Double amountAlreadyPaid = getAmountAlreadyPaid(user);
			Double amountOwed = 0.0;
			Double amountPaidInExcess = 0.0;
			if (totalPrice > amountAlreadyPaid) {
				amountOwed = totalPrice - amountAlreadyPaid;
			} else {
				amountPaidInExcess = amountAlreadyPaid - totalPrice;
			}
			System.out
					.format("%10s%25s%20s%20s%20s", user, totalPrice, amountAlreadyPaid, amountOwed, amountPaidInExcess)
					.println();
		}
	}

	/**
	 * Get the total amount already paid by a user
	 * @param user
	 * @return
	 */
	private Double getAmountAlreadyPaid(String user) {
		return payments.stream().filter(p -> p.getUser().equals(user)).mapToDouble(p -> p.getAmount()).sum();
	}
   
	/**
     * Get the price of an order
     * @param order
     * @return
     */
	private Double getPriceForOrder(Order order) {
		Optional<Product> product = products.stream().filter(p -> p.getDrinkName().equals(order.getDrink()))
				.findFirst();
		if (product.isPresent()) {
			return product.get().getPrices().getPrice(order.getSize());
		}
		return 0.0;
	}
}
