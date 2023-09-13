package br.com.rldcarvalho.jornadaMilhas.model.destination;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DestinationSimpleData(
        @NotBlank
        String name,
        @NotBlank
        String photoPath,
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 5, fraction=2)
        BigDecimal price
) {
}
