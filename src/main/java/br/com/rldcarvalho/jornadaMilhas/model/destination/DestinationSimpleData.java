package br.com.rldcarvalho.jornadaMilhas.model.destination;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Simple data for a destination")
public record DestinationSimpleData(
        @Schema(description = "Name of the destination", required = true)
        @NotBlank
        String name,

        @Schema(description = "Path of the first photo", required = true)
        @NotBlank
        String photoPath,

        @Schema(description = "Path of the second photo", required = true)
        @NotBlank
        String photoPath2,

        @Schema(description = "Meta information about the destination", required = true)
        @NotBlank
        @Size(max = 160)
        String meta,

        @Schema(description = "Description of the destination")
        String description,

        @Schema(description = "Price of the destination", required = true)
        @NotBlank
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 5, fraction = 2)
        BigDecimal price
) {
}
