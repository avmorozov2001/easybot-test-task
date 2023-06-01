package com.example.easybottesttask.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.example.easybottesttask.entity.PCType;
import com.example.easybottesttask.entity.ProductType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Slf4j
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    @NotNull(message = "Необходимо указать номер серии товара")
    private String serial;
    @NotNull(message = "Необходимо указать производителя")
    private String manufacturer;
    @NotNull(message = "Необходимо указать цену товара")
    private BigDecimal price;
    @NotNull(message = "Необходимо указать остаток товара")
    private BigInteger stock;
    private BigDecimal volume;
    private BigDecimal diagonal;
    private PCType pcType;
    @NotNull(message = "Необходимо указать тип продукта - (PC, LAPTOP, HARD_DISK, MONITOR")
    private ProductType productType;

    @AssertTrue(message = "Необходимо указать тип ПК - (DESKTOP, NET_TOP, MONOBLOCK)")
    private boolean isPcType() {
        return (getProductType() != ProductType.PC) || (getPcType() != null);
    }

    @AssertTrue(message = "Необходимо указать корректную диагональ")
    private boolean isDiagonal() {
        var laptopDiagonals = List.of(
                BigDecimal.valueOf(13),
                BigDecimal.valueOf(14),
                BigDecimal.valueOf(15),
                BigDecimal.valueOf(17)
        );
        return ((getProductType() != ProductType.MONITOR) || (getDiagonal() != null)) &&
                (getProductType() != ProductType.LAPTOP) || ((getDiagonal() != null)
                && (laptopDiagonals.contains(getDiagonal())));
    }

    @AssertTrue(message = "Необходимо указать объем жесткого диска")
    private boolean isVolume() {
        return (getProductType() != ProductType.HARD_DISK) || (getVolume() != null);
    }
}
