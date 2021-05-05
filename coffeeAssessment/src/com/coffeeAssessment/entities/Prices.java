package com.coffeeAssessment.entities;

import com.google.gson.annotations.SerializedName;

public class Prices {

@SerializedName("small")
private Double small;
@SerializedName("medium")
private Double medium;
@SerializedName("large")
private Double large;
@SerializedName("huge")
private Double huge;
@SerializedName("mega")
private Double mega;
@SerializedName("ultra")
private Double ultra;

public Double getSmall() {
return small;
}

public void setSmall(Double small) {
this.small = small;
}

public Double getMedium() {
return medium;
}

public void setMedium(Double medium) {
this.medium = medium;
}

public Double getLarge() {
return large;
}

public void setLarge(Double large) {
this.large = large;
}

public Double getHuge() {
return huge;
}

public void setHuge(Double huge) {
this.huge = huge;
}

public Double getMega() {
return mega;
}

public void setMega(Double mega) {
this.mega = mega;
}

public Double getUltra() {
return ultra;
}

public void setUltra(Double ultra) {
this.ultra = ultra;
}
public Double getPrice(String size) {
    Double resultPrice;
    switch (size) {
        case "small":
        	resultPrice = getSmall();
            break;
        case "medium":
            resultPrice = getMedium();
            break;
        case "large":
            resultPrice = getLarge();
            break;
        case "huge":
            resultPrice = getHuge();
            break;
        case "mega":
            resultPrice = getMega();
            break;
        case "ultra":
            resultPrice = getUltra();
            break;
        default:
            resultPrice = 0.0;
            break;
    }
    return resultPrice;
}
}