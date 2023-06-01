package com.example.easybottesttask.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Entity
@Table(name = "pcs")
@DiscriminatorValue(ProductType.PC_NAME)
public class PC extends AbstractProduct {
    @Enumerated(EnumType.STRING)
    private PCType type;
}
