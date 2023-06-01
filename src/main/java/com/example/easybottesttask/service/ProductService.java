package com.example.easybottesttask.service;

import com.example.easybottesttask.dto.ProductDto;
import com.example.easybottesttask.entity.*;
import com.example.easybottesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository abstractProductRepository;

    public <T extends AbstractProduct> List<T> findAllProductsByProductType(ProductType productType) {
        return (List<T>) abstractProductRepository.findAbstractProductsByDtype(productType.name());
    }

    public  <T extends AbstractProduct> T findProductById(Long id) {
        return (T) abstractProductRepository.findById(id).orElse(null);
    }

    public <T extends AbstractProduct> T updateProduct(Long id, ProductDto productDto) {
        var product = findProductById(id);
        if (product == null) return null;


        var laptopDiagonals = List.of(
                BigDecimal.valueOf(13),
                BigDecimal.valueOf(14),
                BigDecimal.valueOf(15),
                BigDecimal.valueOf(17)
        );


        if (productDto.getPrice() != null) product.setPrice(productDto.getPrice());
        if (productDto.getManufacturer() != null) product.setManufacturer(productDto.getManufacturer());
        if (productDto.getSerial() != null) product.setSerial(productDto.getSerial());
        if (productDto.getStock() != null) product.setStock(productDto.getStock());
        if (product.getClass() == PC.class && productDto.getPcType() != null)
            ((PC) product).setType(productDto.getPcType());
        if (product.getClass() == Monitor.class && productDto.getDiagonal() != null)
            ((Monitor) product).setDiagonal(productDto.getDiagonal());
        if (product.getClass() == HardDisk.class && productDto.getVolume() != null)
            ((HardDisk) product).setVolume(productDto.getVolume());
        if (product.getClass() == Laptop.class && productDto.getDiagonal() != null
                && laptopDiagonals.contains(productDto.getDiagonal()))
            ((Laptop) product).setDiagonal(productDto.getDiagonal().intValue());
        return (T) abstractProductRepository.save(product);
    }

    public <T extends AbstractProduct> T createProduct(ProductDto productDto) {
        AbstractProduct product;
        switch (productDto.getProductType()) {
            case PC: {
                product = new PC();
                ((PC) product).setType(productDto.getPcType());
                break;
            }
            case LAPTOP: {
                product = new Laptop();
                ((Laptop) product).setDiagonal(productDto.getDiagonal().intValue());
                break;
            }
            case HARD_DISK: {
                product = new HardDisk();
                ((HardDisk) product).setVolume(productDto.getVolume());
                break;
            }
            case MONITOR: {
                product = new Monitor();
                ((Monitor) product).setDiagonal(productDto.getDiagonal());
                break;
            }
            default: {
                return null;
            }
        }

        product.setManufacturer(productDto.getManufacturer());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());
        product.setSerial(productDto.getSerial());
        return (T) abstractProductRepository.saveAndFlush(product);
    }

    public ProductService(ProductRepository abstractProductRepository) {
        this.abstractProductRepository = abstractProductRepository;
    }

}
