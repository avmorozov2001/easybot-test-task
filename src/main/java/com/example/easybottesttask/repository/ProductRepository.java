package com.example.easybottesttask.repository;

import com.example.easybottesttask.entity.AbstractProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<AbstractProduct, Long> {
    List<AbstractProduct> findAbstractProductsByDtype(String dtype);
}
