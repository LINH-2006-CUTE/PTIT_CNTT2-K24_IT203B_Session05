package model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private String id;
    private Map<MenuItem, Integer> items = new HashMap<>();
    private double totalPrice;
    private OrderStatus status;

    public Order(String id, Map<MenuItem, Integer> items, double totalPrice, OrderStatus status) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public void setItems(Map<MenuItem, Integer> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item,items.getOrDefault(item,0)+quantity);
        totalPrice += item.calculatedPrice() * quantity;
    }
}
