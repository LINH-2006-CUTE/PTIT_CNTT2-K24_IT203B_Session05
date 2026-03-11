package service;

import exception.InvalidOrderIdException;
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



    @Override
    public void create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Dữ liệu đơn hàng không hợp lệ");
        }
        if(OrderRepository.findOrderById(order.getId()) != null){
            System.out.println("Id đơn hàng đã tồn tại!");
            return;
        }
        OrderRepository.orders.add(order);
        System.out.println("Tạo đơn hàng thành công!");
    }

    @Override
    public void updateStatus(String id, OrderStatus orderStatus) throws InvalidOrderIdException {
        Order order = OrderRepository.findOrderById(id);
        if (order == null) {
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng ID: " + id);
        }
        order.setStatus(orderStatus);
        System.out.println("Cập nhật trạng thái thành công!");
    }

    @Override
    public void addItem(String orderId, String itemId, int quantity) throws InvalidOrderIdException {
        Order order = OrderRepository.findOrderById(orderId);

        if (order == null) {
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng ID: " + orderId);
        }
        MenuItem item = MenuRepository.findItemById(itemId);
        if (item == null) {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        order.addItem(item, quantity);
        System.out.println("Thêm món thành công!");
    }

    @Override
    public void displayOrder(String orderId) throws InvalidOrderIdException {
        Order order = OrderRepository.findOrderById(orderId);

        if (order == null) {
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng ID: " + orderId);
        }
        System.out.println("Đơn hàng : ");
        System.out.println(order);
        System.out.println("Danh sách sản phẩm trong đơn hàng : ");
        Map<MenuItem, Integer> items = order.getItems();
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            System.out.println(item + " Số lượng : " + entry.getValue());
        }
    }
}
