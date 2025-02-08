package com.springcourse.simpleCrud.route.product_type;

import com.springcourse.simpleCrud.model.schema.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository repository;

    public List<ProductType> getAllProductType() {
        return repository.findAll();
    }
}
