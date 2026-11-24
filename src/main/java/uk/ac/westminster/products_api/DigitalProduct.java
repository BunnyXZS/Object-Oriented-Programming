package uk.ac.westminster.products_api;

import java.io.Serializable;

public class DigitalProduct extends Product implements Discountable {
    private double discountedPrice;
    @Override
    public void applyDiscount(double rate) {
        // nothing is shipped, so the full rate applies
        this.discountedPrice = getPrice() * (1 - rate);
    }

    @Override
    public double getDiscountedPrice() {
        return discountedPrice;
    }


    private String downloadUrl;
    private double fileSizeMB;
    // no-arg constructor — Jackson needs this to build
// an object from an incoming JSON request body
    public DigitalProduct() {}
    public DigitalProduct(Long id, String name, double price,
                          String downloadUrl, double fileSizeMB) {
        super(id, name, price); // MUST be the first statement
        this.downloadUrl = downloadUrl;
        this.fileSizeMB = fileSizeMB;
    }
    public String getDownloadUrl() {
        return downloadUrl;
    }
    public double getFileSizeMB() {
        return fileSizeMB;
    }
    @Override
    public String getSummary() {
        return "Digital: " + getName()
                + " | Download: " + downloadUrl;
    }


}

