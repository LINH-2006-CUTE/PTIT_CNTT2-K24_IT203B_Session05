package run;

import model.*;
import repository.MenuRepository;
import service.MenuManagement;
import service.OrderManagement;
import service.SearchService;
import service.StatisticService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuConsole {
    static MenuManagement menuManagement = new MenuManagement();
    static SearchService searchService = new SearchService();
    static OrderManagement orderManagement = new OrderManagement();
    static StatisticService statisticService = new StatisticService();

    // Menu Item
    public static void MenuItemConsole(Scanner sc) {
        sc.nextLine();
        int choice;
        do {
            System.out.println("===== MENU MANAGEMENT =====");
            System.out.println("1. Thêm đồ ăn");
            System.out.println("2. Cập nhập thông tin đồ ăn");
            System.out.println("3. Xoá đồ ăn");
            System.out.println("4. Tìm kiếm món ăn theo tên hoặc theo giá");
            System.out.println("5. Hiển thị tất cả các món ăn");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    createMenuItem(sc);
                    break;
                case 2:
                    updateMenuItem(sc);
                    break;
                case 3:
                    deleteMenuItem(sc);
                    break;
                case 4:
                    int subChoice;
                    do {
                        System.out.println("""
                                1. Tìm kiếm theo giá
                                2. Tìm kiếm theo tên
                                0. Thoát
                                """);
                        System.out.print("Lựa chọn của bạn: ");
                        subChoice = Integer.parseInt(sc.nextLine());
                        switch (subChoice) {
                            case 1:
                                searchPriceService(sc);
                                break;
                            case 2:
                                searchNameService(sc);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }
                    } while (subChoice != 0);
                    break;
                case 5:
                    menuManagement.displayAll();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }

    // Menu order
    public static void OrderConsole(Scanner sc) {
        sc.nextLine();
        int choice;
        do {
            System.out.println("===== ORDER MANAGEMENT =====");
            System.out.println("1. Tạo đơn hàng mới cho khách hàng");
            System.out.println("2. Thêm món ăn vào giỏ hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng");
            System.out.println("4. Tìm kiếm đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    createOrder(sc);
                    break;
                case 2:
                    addItemToOrder(sc);
                    break;
                case 3:
                    updateOrderStatus(sc);
                    break;
                case 4:
                    searchOrder(sc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);

    }

    // Menu service
    public static void StatisticAndSearchConsole(Scanner sc) {
        sc.nextLine();
        int choice;
        do {
            System.out.println("===== STATISTIC & SEARCH =====");
            System.out.println("1. Tìm kiếm món ăn theo giá hoặc theo tên");
            System.out.println("2. Thống kê tổng doanh thu");
            System.out.println("3. Các món ăn bán chạy nhất");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    int subChoice;
                    do {
                        System.out.println("""
                                1. Tìm kiếm theo giá
                                2. Tìm kiếm theo tên
                                0. Thoát
                                """);
                        System.out.print("Lựa chọn của bạn: ");
                        subChoice = Integer.parseInt(sc.nextLine());
                        switch (subChoice) {
                            case 1:
                                searchPriceService(sc);
                                break;
                            case 2:
                                searchNameService(sc);
                                break;
                            case 0:
                                break;
                        }
                    } while (subChoice != 0);
                    break;
                case 2:
                    statisticWithMonth(sc);
                    break;
                case 3:
                    topSeller();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 0);
    }

    // Thêm mới món ăn
    public static void createMenuItem(Scanner sc) {
        System.out.println("Chọn loại món");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Dessert");
        System.out.print("Lựa chọn: ");

        int type = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập giá cơ bản: ");
        double basePrice;
        try {
            basePrice = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Giá tiền không hợp lệ!");
            return;
        }
        MenuItem item = null;

        switch (type) {
            case 1:
                System.out.print("Nhập mô tả món ăn: ");
                String description = sc.nextLine();
                item = new Food(id, name, basePrice, description);
                break;
            case 2:
                System.out.print("Nhập size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, basePrice, size);
                break;
            case 3:
                System.out.print("Có đường không? (true/false): ");
                boolean isSweet = Boolean.parseBoolean(sc.nextLine());
                item = new Dessert(id, name, basePrice, isSweet);
                break;
            default:
                System.out.println("Loại món không hợp lệ!");
                return;
        }
        menuManagement.create(item);
    }

    // Cập nhật món ăn
    public static void updateMenuItem(Scanner sc) {
        System.out.print("Nhập ID cần sửa: ");
        String id = sc.nextLine();

        System.out.println("Chọn loại món mới:");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Dessert");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Tên mới: ");
        String name = sc.nextLine();

        System.out.print("Giá mới: ");
        double basePrice = Double.parseDouble(sc.nextLine());
        MenuItem item = null;
        switch (type) {
            case 1:
                System.out.print("Mô tả: ");
                String description = sc.nextLine();
                item = new Food(id, name, basePrice, description);
                break;
            case 2:
                System.out.print("Size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, basePrice, size);
                break;
            case 3:
                System.out.print("Có đường? (true/false): ");
                boolean isSweet = Boolean.parseBoolean(sc.nextLine());
                item = new Dessert(id, name, basePrice, isSweet);
                break;
            default:
                System.out.println("Loại không hợp lệ");
                return;
        }
        menuManagement.update(id, item);
    }

    // Xoá món ăn
    public static void deleteMenuItem(Scanner sc) {
        System.out.print("Nhập ID cần xóa: ");
        String id = sc.nextLine();
        menuManagement.delete(id);
    }

    // Tìm kiếm món ăn theo tên
    public static void searchNameService(Scanner sc) {
        System.out.print("Nhập tên món ăn cần tìm kiếm");
        String nameItem = sc.nextLine();
        List<MenuItem> result = searchService.findByName(nameItem);
        result.forEach(System.out::println);
    }

    // Tìm kiếm món ăn theo giá
    public static void searchPriceService(Scanner sc) {
        System.out.print("Nhập vào giá nhỏ nhất: ");
        double minPrice = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập vào giá lớn nhất: ");
        double maxPrice = Double.parseDouble(sc.nextLine());

        List<MenuItem> results = searchService.findByPriceRange(minPrice, maxPrice);
        if (results != null) {
            results.forEach(System.out::println);
        }
    }

    // Tạo order
    public static void createOrder(Scanner sc) {
        System.out.print("Nhập ID đơn hàng: ");
        String id = sc.nextLine();
        Order order = new Order(id);
        orderManagement.create(order);
    }

    // Thêm món vào order
    public static void addItemToOrder(Scanner sc) {
        System.out.print("Nhập ID đơn hàng: ");
        String orderId = sc.nextLine();

        System.out.print("Nhập ID món ăn: ");
        String itemId = sc.nextLine();

        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        orderManagement.addItem(orderId, itemId, quantity);
    }

    //Cập nhật trạng thái đơn hàng
    public static void updateOrderStatus(Scanner sc) {
        int choice;
        System.out.print("Nhập ID đơn hàng: ");
        String orderId = sc.nextLine();

        do {
            System.out.println("---------Danh sách trạng thái---------");
            System.out.println("1. PENDING");
            System.out.println("2. PAID");
            System.out.println("3. CANCELLED");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> orderManagement.updateStatus(orderId, OrderStatus.PENDING);
                case 2 -> orderManagement.updateStatus(orderId, OrderStatus.PAID);
                case 3 -> orderManagement.updateStatus(orderId, OrderStatus.CANCELLED);
                default -> System.out.println("Lựa chọn không phù hợp!");
            }
        } while (choice != 0);

    }

    // tìm kiếm đơn hàng
    public static void searchOrder(Scanner sc) {
        System.out.print("Nhập ID đơn hàng: ");
        String orderId = sc.nextLine();
        orderManagement.displayOrder(orderId);
    }

    // Doanh thu theo tháng
    public static void statisticWithMonth(Scanner sc) {
        System.out.println("Nhập tháng cần tính : ");
        int month = Integer.parseInt(sc.nextLine());
        double total = statisticService.calculatedTotalRevenue(month);
        if (total > 0) {
            System.out.println("Doanh thu của tháng " + month + " : " + total);
        }
    }

    // top seller
    public static void topSeller() {
        System.out.println("Danh sách sản phẩm bán chạy nhất hiện tại : ");
        Map<MenuItem, Integer> topItems = statisticService.getTopSellingItems();
        topItems.forEach((item, quantity) -> {
            System.out.println(item + " - " + quantity);
        });
    }
}
