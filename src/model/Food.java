package model;

public class Food extends MenuItem{
    private String description;

    public Food(String id, String name, double basePrice, String description) {
        super(id, name, basePrice);
        this.description = description;
    }

    @Override
    public double calculatedPrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return String.format("[Food] %s - %s - Giá: %.0f", getName(), description, calculatedPrice());
    }
}
