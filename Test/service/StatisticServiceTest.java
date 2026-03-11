package service;

import model.Food;
import model.MenuItem;
import model.Order;
import model.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticServiceTest {
    StatisticService statisticService = new StatisticService();

    @BeforeEach
    void setUp() {
        statisticService = new StatisticService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test tính tổng doanh thu")
    void calculatedTotalRevenue() {
        MenuItem burger = new Food("M01", "Burger", 36000.0, "Ngon");
        MenuItem coke = new Food("M02", "Coke", 15000.0, "Nước");

        Map<MenuItem, Integer> items = new HashMap<>();
        items.put(burger, 1);
        items.put(coke, 1);

        Order order = new Order("O01", items, 51000.0, OrderStatus.PAID);

        OrderRepository.orders.add(order);

        assertEquals(51000.0 , statisticService.calculatedTotalRevenue());
    }
}