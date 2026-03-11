package model;

public class Dessert extends MenuItem {
    private boolean isSweet; // Có đường hoặc không đường

    public Dessert(String id, String name, double basePrice, boolean isSweet) {
        super(id, name, basePrice);
        this.isSweet = isSweet;
    }

    @Override
    public double calculatedPrice() {

        return getBasePrice();
    }

    @Override
    public String toString() {
        return String.format("[Dessert] %s - Ngọt: %s - Giá: %.0f", getName(), (isSweet ? "Có" : "Không"), calculatedPrice());
    }
}
