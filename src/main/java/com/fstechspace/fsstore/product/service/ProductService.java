
package com.fstechspace.fsstore.product.service;

import java.util.List;

import com.fstechspace.fsstore.product.dto.ProductRequest;
import com.fstechspace.fsstore.product.dto.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

}

