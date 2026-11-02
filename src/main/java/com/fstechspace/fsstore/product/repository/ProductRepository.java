package com.fstechspace.fsstore.product.repository; 

import com.fstechspace.fsstore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface ProductRepository extends JpaRepository<Product, Long> { 
 
}
