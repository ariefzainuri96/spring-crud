package com.springcourse.simpleCrud.route.product_type;

import com.springcourse.simpleCrud.model.schema.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}
