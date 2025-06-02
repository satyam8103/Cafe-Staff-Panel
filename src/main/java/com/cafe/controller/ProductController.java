package com.cafe.controller;

import com.cafe.Dto.ProductDTO;
import com.cafe.service.Interface.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    /**
     * Creates a new product.
     *
     * @param productDTO the product data
     * @return the created product
     */
    @Operation(summary = "Create a new product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Request to create product: {}", productDTO);
        try {
            ProductDTO created = productService.createProduct(productDTO);
            logger.info("Product created with ID: {}", created.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException ex) {
            logger.error("Product creation failed: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    /**
     * Retrieves all products.
     *
     * @return list of products
     */
    @Operation(summary = "Get all products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Request to get all products");
        List<ProductDTO> products = productService.getAllProducts();
        logger.info("Retrieved {} products", products.size());
        return ResponseEntity.ok(products);
    }

    /**
     * Enables a product by ID.
     *
     * @param id the product ID
     * @return success message
     */
    @Operation(summary = "Enable a product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product enabled successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PostMapping("/{id}/enable")
    public ResponseEntity<Map<String, String>> enableProduct(@PathVariable Long id) {
        logger.info("Request to enable product with ID: {}", id);
        try {
            productService.toggleProductStatus(id, true);
            logger.info("Product enabled with ID: {}", id);
            return ResponseEntity.ok(Map.of("message", "Product enabled successfully"));
        } catch (IllegalArgumentException ex) {
            logger.error("Enable product failed: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Disables a product by ID.
     *
     * @param id the product ID
     * @return success message
     */
    @Operation(summary = "Disable a product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product disabled successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PostMapping("/{id}/disable")
    public ResponseEntity<Map<String, String>> disableProduct(@PathVariable Long id) {
        logger.info("Request to disable product with ID: {}", id);
        try {
            productService.toggleProductStatus(id, false);
            logger.info("Product disabled with ID: {}", id);
            return ResponseEntity.ok(Map.of("message", "Product disabled successfully"));
        } catch (IllegalArgumentException ex) {
            logger.error("Disable product failed: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Handles unexpected exceptions.
     *
     * @param ex the exception
     * @return error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        logger.error("Internal server error: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Internal server error", "details", ex.getMessage()));
    }
}