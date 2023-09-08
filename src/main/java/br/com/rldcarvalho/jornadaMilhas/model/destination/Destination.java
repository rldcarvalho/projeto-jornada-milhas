package br.com.rldcarvalho.jornadaMilhas.model.destination;

import jakarta.persistence.*;

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
    private BigDecimal price;
    private boolean active;

    public Destination() {}

    public Destination(Long id, String name, String photoPath, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.price = price;
        this.active = true;
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
