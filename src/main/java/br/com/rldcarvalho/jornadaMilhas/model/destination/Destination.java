package br.com.rldcarvalho.jornadaMilhas.model.destination;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "Destination")
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photoPath;
    private String photoPath2;
    private String meta;
    private String description;
    private BigDecimal price;
    private boolean active;

    public Destination() {}

    public Destination(Long id, String name, String photoPath, String photoPath2, String meta, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.photoPath2 = photoPath2;
        this.meta = meta;
        this.description = description;
        this.price = price;
        this.active = true;
    }

    public Destination(@Valid DestinationSimpleData destinationSimpleData){
        this.name = destinationSimpleData.name();
        this.photoPath = destinationSimpleData.photoPath();
        this.photoPath2 = destinationSimpleData.photoPath2();
        this.meta = destinationSimpleData.meta();
        this.description = destinationSimpleData.description();
        this.price = destinationSimpleData.price();
        this.active = true;
    }

    public void dataUpdate(@Valid DestinationSimpleData destinationDetailData) {
        this.name = destinationDetailData.name();
        this.photoPath = destinationDetailData.photoPath();
        this.photoPath2 = destinationDetailData.photoPath2();
        this.meta = destinationDetailData.meta();
        this.description = destinationDetailData.description();
        this.price = destinationDetailData.price();
    }

    public void delete(){
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getPhotoPath2() {
        return photoPath2;
    }

    public String getMeta() {
        return meta;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
