package service;

import exception.InvalidOrderIdException;
import model.MenuItem;
import model.Order;
import model.OrderStatus;

import java.util.Scanner;

public interface IOrderService {
    void create(Order order);
    void updateStatus(String id, OrderStatus orderStatus);
    void addItem(String orderId,String itemId, int quantity);
}
