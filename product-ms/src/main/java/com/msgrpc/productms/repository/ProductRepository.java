package com.msgrpc.productms.repository;

import com.msgrpc.productms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByRestaurantId(Long restaurantId);
}
