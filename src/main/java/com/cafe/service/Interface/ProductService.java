package com.cafe.service.Interface;

import com.cafe.Dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    void toggleProductStatus(Long productId, boolean isEnabled);
    void deleteProduct(Long id);
}
