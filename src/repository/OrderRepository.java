package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public static List<Order> orders = new ArrayList<Order>();

    public static Order findOrderById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> findAll() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
