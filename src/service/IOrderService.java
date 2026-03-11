package service;

import exception.InvalidOrderIdException;
import model.MenuItem;
import model.Order;
import model.OrderStatus;

import java.util.Scanner;

public interface IOrderService {
    void create(Order order);
    void updateStatus(String id, OrderStatus orderStatus) throws InvalidOrderIdException;
    void addItem(String orderId,String itemId, int quantity) throws InvalidOrderIdException;
    void displayOrder(String orderId) throws InvalidOrderIdException;
}
