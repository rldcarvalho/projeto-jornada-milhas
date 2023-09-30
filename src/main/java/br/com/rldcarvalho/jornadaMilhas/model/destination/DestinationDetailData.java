package br.com.rldcarvalho.jornadaMilhas.model.destination;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Details of a destination")
public record DestinationDetailData(
        @Schema(description = "ID of the destination")
        Long id,

        @Schema(description = "Name of the destination")
        String name,

        @Schema(description = "Path of the first photo")
        String photoPath,

        @Schema(description = "Path of the second photo")
        String photoPath2,

        @Schema(description = "Meta information about the destination")
        String meta,

        @Schema(description = "Description of the destination")
        String description,

        @Schema(description = "Price of the destination")
        BigDecimal price
) {
    public DestinationDetailData(Destination destination) {
        this(
            destination.getId(),
            destination.getName(),
            destination.getPhotoPath(),
            destination.getPhotoPath2(),
            destination.getMeta(),
            destination.getDescription(),
            destination.getPrice()
        );
    }
}
