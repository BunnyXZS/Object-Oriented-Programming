package uk.ac.westminster.products_api;

public interface Discountable {
    // A contract. No bodies, no fields, no constructor.
// Any class that implements this promises to provide both.
    void applyDiscount(double rate);
    double getDiscountedPrice();
}
