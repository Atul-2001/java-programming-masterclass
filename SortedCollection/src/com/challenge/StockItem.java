package com.challenge;

import java.util.LinkedHashMap;
import java.util.Map;

public class StockItem implements Comparable<StockItem> {

    private final String name;
    private double price;
    private int quantityStock;
    private final Map<Basket, Integer> reserved;

    public StockItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantity;
        reserved = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

    public int reserveItem(Basket basket, int quantity) {
        if (basket != null && quantity > 0 && quantity <= this.quantityStock) {
            if (reserved.containsKey(basket)) {
                int resQuantity = reserved.get(basket);
                reserved.put(basket, (resQuantity + quantity));
                return resQuantity;
            } else {
                reserved.put(basket, quantity);
                return quantity;
            }
        } else {
            return 0;
        }
    }

    public int unReserveItem(Basket basket, int quantity) {
        if (basket != null && quantity > 0 && quantity <= reserved.get(basket)) {
            if (reserved.containsKey(basket)) {
                if ((reserved.get(basket) - quantity) == 0) {
                    return reserved.remove(basket);
                } else {
                    int resQuantity = (reserved.get(basket) - quantity);
                    return reserved.put(basket, resQuantity);
                }
            }
        }
        return 0;
    }

    public int getReservedQuantity(Basket basket) {
        if (reserved.containsKey(basket)) {
            return reserved.get(basket);
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 100;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj != null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        return this.name.equals(((StockItem) obj).getName());
    }

    @Override
    public int compareTo(StockItem o) {
        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.name.compareToIgnoreCase(o.getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}
