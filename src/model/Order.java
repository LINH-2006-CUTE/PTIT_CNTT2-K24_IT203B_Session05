package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final String id;
    private Map<MenuItem, Integer> items = new HashMap<>();
    private double totalPrice;
    private OrderStatus status;
    private final LocalDateTime createdAt;

    public Order(String id) {
        this.id = id;
        this.items = new HashMap<>();
        this.totalPrice = 0;
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item,items.getOrDefault(item,0)+quantity);
        totalPrice += item.calculatedPrice() * quantity;
    }

    @Override
    public String toString() {
        return "ID : " + id + " Total Price : " + totalPrice + " Status : " + status;
    }
}
