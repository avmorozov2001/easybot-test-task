package com.example.easybottesttask.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING, length = 60)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "products")
abstract public class AbstractProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serial;
    private String manufacturer;
    private BigDecimal price;
    private BigInteger stock;

    @JsonIgnore
    @Column(name = "DTYPE", insertable = false, updatable = false)
    private String dtype;

    @JsonProperty(value = "productType")
    @Transient
    public String getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : val.value();
    }
}
