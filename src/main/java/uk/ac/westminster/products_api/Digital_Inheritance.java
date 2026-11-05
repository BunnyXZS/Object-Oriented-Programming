package uk.ac.westminster.products_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class Digital_Inheritance {

    // ---------- Parent (super) class: what every product has ----------
    public static class Product {
        private String productId;
        private String name;
        private double price;

        public Product() { }                       // needed so Spring can build it from JSON

        public Product(String productId, String name, double price) {
            this.productId = productId;
            this.name = name;
            this.price = price;
        }

        public String describe() {
            return productId + " - " + name + " - GBP " + price;
        }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }

    // ---------- Child class: a DigitalProduct IS A Product ----------
    // It inherits productId, name, price and describe(), and adds fileSizeMb.
    public static class DigitalProduct extends Product {
        private double fileSizeMb;

        public DigitalProduct() { }

        public DigitalProduct(String productId, String name, double price, double fileSizeMb) {
            super(productId, name, price);         // call the parent constructor
            this.fileSizeMb = fileSizeMb;
        }

        @Override
        public String describe() {                 // override and extend the parent method
            return super.describe() + " - download " + fileSizeMb + " MB";
        }

        public double getFileSizeMb() { return fileSizeMb; }
        public void setFileSizeMb(double fileSizeMb) { this.fileSizeMb = fileSizeMb; }
    }

    // In-memory store: holds both Products and DigitalProducts (polymorphism)
    private final List<Product> products = new ArrayList<>();

    // POST a normal product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // POST a digital product: the JSON body includes the inherited fields too
    @PostMapping("/digital")
    public ResponseEntity<DigitalProduct> addDigitalProduct(@RequestBody DigitalProduct product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // GET everything: each object runs its own describe()
    @GetMapping
    public List<String> getAllProducts() {
        List<String> result = new ArrayList<>();
        for (Product p : products) {
            result.add(p.describe());
        }
        return result;
    }
}