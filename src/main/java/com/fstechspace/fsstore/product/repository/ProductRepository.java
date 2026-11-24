package com.fstechspace.fsstore.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fstechspace.fsstore.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
