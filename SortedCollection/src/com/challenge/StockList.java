package com.challenge;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {

    private final Map<String, StockItem> list;

    public StockList() {
        list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem newItem = list.getOrDefault(item.getName(), item);

            if (newItem != item) {
                item.adjustStock(newItem.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        } else {
            return -1;
        }
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if ((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }

        return 0;
    }

    public StockItem get(String key) {
        return list.getOrDefault(key, null);
    }

    public Map<String, Double> priceList() {
        Map<String, Double> price = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            price.put(item.getKey(), item.getValue().getPrice());
        }

        return Collections.unmodifiableMap(price);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List :-\n";
        double totalCost = 0.0;

        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ((stockItem.quantityInStock() > 0 ) ?
                    (". There are " + stockItem.quantityInStock() + " in stock. Value of items : " + String.format("%.2f", itemValue) + "\n") :
                    (". This item is Out of Stock!\n"));

            totalCost += itemValue;
        }

        return s + "Total stock value " + String.format("%.2f", totalCost);

//        return "StockList{" +
//                "list=" + list +
//                '}';
    }
}
