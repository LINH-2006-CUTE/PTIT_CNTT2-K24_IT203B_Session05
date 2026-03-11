package service;

import model.MenuItem;
import model.Order;
import model.OrderStatus;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static repository.MenuRepository.menuItems;

public class OrderManagement implements IOrderService{
    private OrderRepository orders = new OrderRepository();
    private MenuRepository menus = new MenuRepository();

    public MenuItem findItemById(String id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void create(Order order) {
        orders.addOrder(order);
    }

    @Override
    public void updateStatus(String id, OrderStatus orderStatus) {
        Order order = orders.findOrderById(id);

        if (order != null) {
            order.setStatus(orderStatus);
        }else {
            System.out.println("Không tìm thấy đơn hàng");
        }
    }

    @Override
    public void addItem(String orderId, String itemId, int quantity) {
        Order order = orders.findOrderById(orderId);
        MenuItem item = findItemById(itemId);

        if (order == null) {
            System.out.println("Không tìm thấy đơn hàng");
        }

        if (item == null) {
            System.out.println("Không tìm thấy đồ ăn");
        }

        order.addItem(item,  quantity);
    }
}
