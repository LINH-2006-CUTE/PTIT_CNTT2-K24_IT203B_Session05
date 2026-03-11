package service;

import model.Food;
import model.MenuItem;
import org.junit.jupiter.api.*;
import repository.MenuRepository;

import static org.junit.jupiter.api.Assertions.*;


class MenuManagementTest {

    MenuManagement menuManagement = new MenuManagement();

    @BeforeEach
    void setUp() {
        menuManagement = new MenuManagement();
        MenuRepository.menuItems.clear();
        System.out.println("Test case");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test thêm món mới")
    void create() {
        MenuItem newFood = new Food("M01" , "Sút sít" , 36.000 , "Món ăn không ngon");

        menuManagement.create(newFood);

        Assertions.assertEquals(1, MenuRepository.menuItems.size());
    }

    @Test
    @DisplayName("Test cập nhật món ăn")
    void update() {

        MenuItem food = new Food("M01","Burger",36000.0,"Ngon");
        MenuRepository.menuItems.add(food);

        MenuItem updatedFood = new Food("M01","Burger đặc biệt",50000.0,"Rất ngon");

        menuManagement.update("M01", updatedFood);

        MenuItem result = MenuRepository.menuItems.get(0);

        assertEquals("Burger đặc biệt", result.getName());
        assertEquals(50000.0, result.getBasePrice());
    }

    @Test
    @DisplayName("Test xóa món ăn")
    void delete() {

        MenuItem food = new Food("M01","Burger",36000.0,"Ngon");
        MenuRepository.menuItems.add(food);

        menuManagement.delete("M01");

        assertEquals(0, MenuRepository.menuItems.size());
    }


}