
package com.fstechspace.fsstore.product.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fstechspace.fsstore.product.dto.ProductRequest;
import com.fstechspace.fsstore.product.dto.ProductResponse;
import com.fstechspace.fsstore.product.entity.Product;
import com.fstechspace.fsstore.product.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create a product
    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product(
            request.getName().trim(),
            request.getPrice()
        );

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    // Get all products
    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get one product by ID
    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found with id: " + id
                ));

        return mapToResponse(product);
    }

    // Update a product
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found with id: " + id
                ));

        existingProduct.updateName(request.getName().trim());
        existingProduct.updatePrice(request.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);

        return mapToResponse(updatedProduct);
    }

    // Delete a product
    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found with id: " + id
                ));

        productRepository.delete(product);
    }

    // Convert Product entity to ProductResponse DTO
    private ProductResponse mapToResponse(Product product) {

        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getVersion(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }

}
