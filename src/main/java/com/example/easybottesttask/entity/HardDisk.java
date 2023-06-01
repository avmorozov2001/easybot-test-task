package com.example.easybottesttask.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Entity
@Table(name = "hard_disks")
@DiscriminatorValue(ProductType.HARD_DISK_NAME)
public class HardDisk extends AbstractProduct {
    private BigDecimal volume;
}
