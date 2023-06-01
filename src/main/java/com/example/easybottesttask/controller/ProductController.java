package com.example.easybottesttask.controller;

import com.example.easybottesttask.dto.ProductDto;
import com.example.easybottesttask.entity.ProductType;
import com.example.easybottesttask.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductDto productDto) {
            return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PutMapping("{id}")
    public ResponseEntity updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @GetMapping("{productType}")
    public ResponseEntity getAllProducts(@PathVariable ProductType productType) {
        return ResponseEntity.ok(productService.findAllProductsByProductType(productType));
    }

    @GetMapping()
    public ResponseEntity getProductById(@RequestParam(value = "id", required = true) Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
