package com.challenge;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        list = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            if (item.reserveItem(this, quantity) >= quantity) {
                list.put(item, inBasket + quantity);
                return (inBasket + quantity);
            }
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            if (list.getOrDefault(item, 0) != 0) {
                if (item.unReserveItem(this, quantity) != 0) {
                    int q = list.get(item) - quantity;
                    return list.put(item, q);
                }
            }
        }
        return 0;
    }

    public int removeFromBasket(StockItem item) {
        if ((item != null) && (list.containsKey(item))) {
            list.remove(item);
            return item.unReserveItem(this, item.getReservedQuantity(this));
        } else {
            return 0;
        }
    }

    public void checkout(StockList stockList) {
        double totalAmount = 0.0;
        System.out.println("\nList of Item :-\n");

        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            System.out.println(item.getKey().getName() + " : " + item.getKey().getPrice() + " : " + item.getValue() + " : " + (item.getValue() * item.getKey().getPrice()));
            totalAmount += (item.getValue() * item.getKey().getPrice());
        }

        System.out.println("Total Payable : " + totalAmount);
        System.out.println("Please Pay above amount to complete checkout.");
        System.out.println("Processing payment to complete order......");

        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            StockItem resItem = item.getKey();
            if (stockList.sellStock(resItem.getName(), item.getValue()) != 0) {
                item.getKey().unReserveItem(this, item.getKey().getReservedQuantity(this));
            }
        }
        list.clear();
        System.out.println("Thanks for Shopping!");
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
//        return "Basket{" +
//                "name='" + name + '\'' +
//                ", list=" + list +
//                '}';
    }
}
