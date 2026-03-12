package service;

import model.MenuItem;
import model.Order;
import model.OrderStatus;
import repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticService implements IStatisticService {
    @Override
    public double calculatedTotalRevenue(int month) {
        if (month < 1 || month > 12) {
            System.out.println("Tháng không phù hợp!");
            return -1;
        }
        System.out.println("sum : "+OrderRepository.orders.stream().filter(order -> order.getCreatedAt().getMonthValue() == month && order.getStatus() == OrderStatus.PAID)
                .mapToDouble(Order::getTotalPrice)
                .sum());

        return OrderRepository.orders.stream().filter(order -> order.getCreatedAt().getMonthValue() == month && order.getStatus() == OrderStatus.PAID)
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    @Override
    public Map<MenuItem, Integer> getTopSellingItems() {
        Map<MenuItem, Integer> result = new HashMap<>();
        OrderRepository.orders.forEach(order -> {
            order.getItems().forEach((item, quantity) -> {
                result.put(item, result.getOrDefault(item, 0) + quantity);
            });
        });
        return result.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}