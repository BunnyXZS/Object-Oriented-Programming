package uk.ac.westminster.products_api;

public class PhysicalProduct extends Product implements Discountable {

    private double discountedPrice;

    @Override
    public void applyDiscount(double rate) {
        // (a) cap the rate at 0.20 with Math.min
        double cappedRate = Math.min(rate, 0.20);
        // (b) apply the capped rate, not the rate
        this.discountedPrice = getPrice() * (1 - cappedRate);
    }

    @Override
    public double getDiscountedPrice() {
        return discountedPrice;
    }
    private double weightKg;
    private String dimensions;

    // (a) Empty no-arg constructor for Jackson
    public PhysicalProduct() {
    }

    @Override
    public String getSummary() {
        return "";
    }

    // (b) Full constructor
    public PhysicalProduct(Long id, String name, double price, double weightKg, String dimensions) {
        super(id, name, price);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
    }

    // (c) Getters
    public double getWeightKg() {
        return weightKg;
    }

    public String getDimensions() {
        return dimensions;
    }
}