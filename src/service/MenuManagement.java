package service;

import model.MenuItem;
import repository.MenuRepository;
import java.util.Optional;

public class MenuManagement implements IMenuService {
    @Override
    public void create(MenuItem item) {
        if(item == null) {
            System.out.println("Dữ liệu món ăn không được trống");
            return;
        }
        if(MenuRepository.findItemById(item.getId()) != null) {
            System.out.println("Id sản phẩm đã tồn tại!");
            return;
        }

        MenuRepository.menuItems.add(item);
        System.out.println("Đã thêm món ăn thành công");
    }

    @Override
    public void update(String id, MenuItem updateItem) {
        Optional<MenuItem> foundItem = MenuRepository.menuItems.stream().filter(item -> item.getId().equals(id)).findFirst();

        if(foundItem.isPresent()) {
            MenuItem existingItem = foundItem.get();

            existingItem.setName(updateItem.getName());
            existingItem.setBasePrice(updateItem.getBasePrice());

            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Không tìm thấy món ăn");
        }
    }

    @Override
    public void delete(String id) {
        boolean isRemoved = MenuRepository.menuItems.removeIf(item -> item.getId().equals(id));

        if (isRemoved) {
            System.out.println("Đã xóa món ăn thành công");
        } else {
            System.out.println("Không tìm thấy món ăn để xóa");
        }
    }

    @Override
    public void displayAll() {
        if (MenuRepository.menuItems.isEmpty()) {
            System.out.println("Thực đơn hiện tại đang trống");
            return;
        }

        System.out.println("\nDanh sách thực đơn");
        MenuRepository.menuItems.forEach(System.out::println);
    }
}
