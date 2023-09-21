package br.com.rldcarvalho.jornadaMilhas.model.destination;

import java.math.BigDecimal;

public record DestinationDetailData(
        Long id,
        String name,
        String photoPath,
        String photoPath2,
        String meta,
        String description,
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
