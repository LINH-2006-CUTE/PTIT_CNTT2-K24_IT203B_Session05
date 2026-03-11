package model;

public class Drink extends MenuItem {
    private String size; // S, M, L

    public Drink(String id, String name, double basePrice, String size) {
        super(id, name, basePrice);
        this.size = size.toUpperCase();
    }

    @Override
    public double calculatedPrice() {
        // Size M thêm 5k, Size L thêm 10k
        if (size.equals("M")) {
            return getBasePrice() + 5000;
        } else if (size.equals("L")) {
            return getBasePrice() + 10000;
        }
        return getBasePrice();
    }

    @Override
    public String toString() {
        return String.format("[Drink] %s - Size: %s - Giá: %.0f", getName(), size, calculatedPrice());
    }
}
