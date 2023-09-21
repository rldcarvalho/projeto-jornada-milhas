package br.com.rldcarvalho.jornadaMilhas.model.destination;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DestinationSimpleData(
        @NotBlank
        String name,
        @NotBlank
        String photoPath,
        @NotBlank
        String photoPath2,
        @Size(max = 160)
        String meta,
        String description,
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 5, fraction=2)
        BigDecimal price
) {
}
