package com.cafe.service.Impl;
import com.cafe.Dto.ProductDTO;
import com.cafe.entity.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.service.Interface.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setIsEnabled(productDTO.getIsEnabled());
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return modelMapper.map(
                productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found")),
                ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void toggleProductStatus(Long productId, boolean isEnabled) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsEnabled(isEnabled);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
