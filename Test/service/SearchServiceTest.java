package service;

import model.Food;
import model.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.MenuRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {
    SearchService searchService;

    @BeforeEach
    void setUp() {
        searchService = new SearchService();
        MenuRepository.menuItems.clear();

        MenuItem burger = new Food("M01","Burger",36000.0,"Ngon");
        MenuItem coke = new Food("M02","Coke",15000.0,"Nước");
        MenuItem pizza = new Food("M03","Pizza",50000.0,"Ý");

        MenuRepository.menuItems.add(burger);
        MenuRepository.menuItems.add(coke);
        MenuRepository.menuItems.add(pizza);
    }

    @AfterEach
    void tearDown() {
        MenuRepository.menuItems.clear();
    }

    @Test
    @DisplayName("Test tìm món theo tên")
    void findByName() {
        List<MenuItem> result = searchService.findByName("burger");

        assertEquals(1, result.size());
        assertEquals("Burger", result.get(0).getName());
    }

    @Test
    @DisplayName("Test tìm món theo khoảng giá")
    void findByPriceRange() {
        List<MenuItem> result = searchService.findByPriceRange(20000,40000);

        assertEquals(1, result.size());
        assertEquals("Burger", result.get(0).getName());
    }
}