package com.springcourse.simpleCrud.route.product;

import com.springcourse.simpleCrud.model.schema.Product;
import com.springcourse.simpleCrud.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public BaseResponse<List<Product>> getProducts() {
        return new BaseResponse<>(200, "Sukses", productRepository.findAll());
    }

    public BaseResponse<List<Product>> getMyProducts(int userId) {
        return new BaseResponse<>(200, "Sukses", productRepository.findByUserId(userId));
    }

    public BaseResponse<Product> getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);

        return new BaseResponse<>(
                product != null ? 200 : 404,
                String.format("%s", product != null ? "Sukses mendapatkan barang" : "Barang tidak ditemukan"),
                product
        );
    }

    public BaseResponse<Product> addOrUpdateProduct(Optional<Integer> id, Product product) {
        productRepository.save(product);

        return new BaseResponse<>(200, String.format("Berhasil %s produk", id.isPresent() ? "memperbarui" : "menambahkan"), product);
    }

    public BaseResponse<Product> deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new BaseResponse<>(200, "Produk berhasil dihapus", null);
        } else {
            return new BaseResponse<>(404, "Produk tidak ditemukan", null);
        }
    }

    public BaseResponse<List<Product>> searchProduct(String keyword) {
        return new BaseResponse<>(200, "Berhasil mencari produk", productRepository.searchProducts(keyword));
    }
}
