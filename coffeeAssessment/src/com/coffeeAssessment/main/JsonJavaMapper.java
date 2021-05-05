package com.coffeeAssessment.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.coffeeAssessment.entities.Order;
import com.coffeeAssessment.entities.Payment;
import com.coffeeAssessment.entities.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonJavaMapper {
	private List<Order> orders;
	private List<Payment> payments;
	private List<Product> products;

	/*
	 * This method is used to read data from the json files and map it to the
	 * corresponding Java objects
	 */
	public void readJson() {
		Gson gson = new Gson();
		Reader reader1 = null;
		Reader reader2 = null;
		Reader reader3 = null;

		try {
			reader1 = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/orders.json")));
			Type orderListType = new TypeToken<ArrayList<Order>>() {
			}.getType();
			orders = gson.fromJson(reader1, orderListType);
			// orders = gson.fromJson(reader1, Order[].class);
			reader2 = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/payments.json")));
			Type paymentsListType = new TypeToken<ArrayList<Payment>>() {
			}.getType();
			payments = gson.fromJson(reader2, paymentsListType);
			// payments = gson.fromJson(reader2, Payment[].class);
			reader3 = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/products.json")));
			Type productsListType = new TypeToken<ArrayList<Product>>() {
			}.getType();
			products = gson.fromJson(reader3, productsListType);
			// products = gson.fromJson(reader3, Product[].class);
		} catch (Exception e) {
			System.out.println("Exception while reading the file : " + e.getMessage());
		} finally {
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException ex) {
					System.out.println("Exception while closing the file reader : " + ex.getMessage());
				}
			}
			if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException ex) {
					System.out.println("Exception while closing the file reader : " + ex.getMessage());
				}
			}
			if (reader3 != null) {
				try {
					reader3.close();
				} catch (IOException ex) {
					System.out.println("Exception while closing the file reader : " + ex.getMessage());
				}
			}
		}

	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public List<Product> getProducts() {
		return products;
	}
}
