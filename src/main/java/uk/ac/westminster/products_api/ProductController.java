package uk.ac.westminster.products_api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;            // 1. the field

    public ProductController(ProductService productService) { // 2. the constructor
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();              // 3. lowercase p
    }
}