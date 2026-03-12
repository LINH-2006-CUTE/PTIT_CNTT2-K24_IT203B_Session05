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
    OrderManagement orderManagement = new OrderManagement();
    MenuManagement menuManagement = new MenuManagement();

    @BeforeEach
    void setUp() {
        statisticService = new StatisticService();
        orderManagement = new OrderManagement();
        menuManagement = new MenuManagement();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test tính tổng doanh thu")
    void calculatedTotalRevenue() {
        MenuItem burger = new Food("M01", "Burger", 36000.0, "Ngon");
        MenuItem coke = new Food("M02", "Coke", 15000.0, "Nước");

        menuManagement.create(burger);
        menuManagement.create(coke);

        Order order = new Order("O01");
        orderManagement.create(order);

        orderManagement.addItem(order.getId(), burger.getId(), 1);
        orderManagement.addItem(order.getId(), coke.getId(), 1);
        orderManagement.updateStatus(order.getId(), OrderStatus.PAID);

        assertEquals(51000.0, statisticService.calculatedTotalRevenue(3));
    }
}