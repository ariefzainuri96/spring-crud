package com.springcourse.simpleCrud.route.product;

import com.springcourse.simpleCrud.model.schema.Product;
import com.springcourse.simpleCrud.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public BaseResponse<List<Product>> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public BaseResponse<Product> getProductById(
            @PathVariable int id
    ) {
        return productService.getProductById(id);
    }

    @PostMapping("")
    public BaseResponse<Product> addProduct(@RequestBody Product product) {
        return productService.addOrUpdateProduct(Optional.empty(), product);
    }

    @PutMapping("/{id}")
    public BaseResponse<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.addOrUpdateProduct(Optional.of(id), product);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Product> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public BaseResponse<List<Product>> searchProduct(@RequestParam String keyword) {
        return productService.searchProduct(keyword);
    }
}
