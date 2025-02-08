package com.springcourse.simpleCrud.route.product_type;

import com.springcourse.simpleCrud.model.BaseResponse;
import com.springcourse.simpleCrud.model.schema.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-type")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/")
    public BaseResponse<List<ProductType>> getAllProductType() {
        return new BaseResponse<>(200, "Testing", productTypeService.getAllProductType());
    }

}
