package repository;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    public static List<MenuItem> menuItems = new ArrayList<MenuItem>();

    static {
        menuItems.add(new Food("F01", "Burger Bò Đặc Biệt", 75000, "Bò Mỹ, phô mai, rau tươi"));
        menuItems.add(new Food("F02", "Gà Rán Cay", 45000, "Gà giòn tan vị cay nồng"));
        menuItems.add(new Drink("D01", "Trà Sữa Trân Châu", 35000, "L"));
        menuItems.add(new Drink("D02", "Coca Cola", 20000, "M"));
        menuItems.add(new Dessert("S01", "Kem Vanila", 25000, true));
        menuItems.add(new Dessert("S02", "Bánh Flan", 15000, false));
    }

    public static MenuItem findItemById(String id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
