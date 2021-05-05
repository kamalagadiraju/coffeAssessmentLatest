package com.coffeeAssessment.entities;

import com.google.gson.annotations.SerializedName;

public class Product {

@SerializedName("drink_name")
private String drinkName;
@SerializedName("prices")
private Prices prices;

public String getDrinkName() {
return drinkName;
}

public void setDrinkName(String drinkName) {
this.drinkName = drinkName;
}

public Prices getPrices() {
return prices;
}

public void setPrices(Prices prices) {
this.prices = prices;
}

}