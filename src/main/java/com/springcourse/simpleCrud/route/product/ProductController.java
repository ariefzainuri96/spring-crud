package com.springcourse.simpleCrud.route.product;

import com.springcourse.simpleCrud.model.request.ProductFilterRequest;
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
        return productService.getAllProduct();
    }

    @GetMapping("/my/{id}")
    public BaseResponse<List<Product>> getMyProducts(@PathVariable int id) {
        return productService.getMyProducts(id);
    }

    @GetMapping("/{id}")
    public BaseResponse<Product> getProductById(
            @PathVariable int id) {
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

    @PostMapping("/filter")
    public BaseResponse<List<Product>> filterProduct(@RequestBody ProductFilterRequest request) {
        return new BaseResponse<>(200, "Testing", productService.filterProduct(request));
    }
}
