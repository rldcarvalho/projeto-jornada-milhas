package br.com.rldcarvalho.jornadaMilhas.model.destination;

import java.math.BigDecimal;

public record DestinationDetailData(
        Long id,
        String name,
        String photoPath,
        BigDecimal price
) {
    public DestinationDetailData(Destination destination) {
        this(
            destination.getId(),
            destination.getName(),
            destination.getPhotoPath(),
            destination.getPrice()
        );
    }
}
