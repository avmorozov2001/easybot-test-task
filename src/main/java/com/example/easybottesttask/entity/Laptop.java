package com.example.easybottesttask.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Entity
@Table(name = "laptops")
@DiscriminatorValue(ProductType.LAPTOP_NAME)
public class Laptop extends AbstractProduct {
    private int diagonal;
}
