package com.fstechspace.fsstore.product.service; 

import java.util.List; 

import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 

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
            request.getName(),
            request.getPrice()
        );
        
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
    
    // Get one prdocut by ID
    @Override 
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) { 

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToResponse(product);
    }

    // Update a product
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.updateName(request.getName());
        product.updatePrice(request.getPrice());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    // Delete a product
    @Override
    public void deleteProduct(Long id) { 
        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)); 
                
        productRepository.delete(product);
    }

    // Convert Product entity to ProductResponse DTO
    private ProductResponse mapToResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
 
}
