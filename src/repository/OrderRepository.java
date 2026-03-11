package repository;

import model.Order;
import model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public static List<Order> orders = new ArrayList<Order>();

    static {
        // Giả lập đơn hàng 1 (Đã thanh toán)
        Order o1 = new Order("ORD01");
        o1.addItem(MenuRepository.findItemById("F01"), 2);
        o1.setStatus(OrderStatus.PAID);
        orders.add(o1);

        // Giả lập đơn hàng 2 (Đã thanh toán)
        Order o2 = new Order("ORD02");
        o2.addItem(MenuRepository.findItemById("D01"), 3); // 3 Trà sữa
        o2.setStatus(OrderStatus.PAID);
        orders.add(o2);

        // Giả lập đơn hàng 3 (Đang chờ)
        Order o3 = new Order("ORD03");
        o3.addItem(MenuRepository.findItemById("S01"), 1);
        o3.setStatus(OrderStatus.PENDING);
        orders.add(o3);
    }

    public static Order findOrderById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
}
