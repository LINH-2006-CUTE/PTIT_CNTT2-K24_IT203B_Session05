package service;

import model.MenuItem;
import model.Order;
import repository.OrderRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticService implements IStatisticService {
    @Override
    public double calculatedTotalRevenue() {
        return OrderRepository.orders.stream()
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
                .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
