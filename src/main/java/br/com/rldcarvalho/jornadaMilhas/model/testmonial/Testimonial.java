package br.com.rldcarvalho.jornadaMilhas.model.testmonial;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Testimonial")
@Table(name = "testimonial")
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personName;
    private String testimonialText;
    private String imagePath;
    private boolean active;

    public Testimonial(TestimonialCreateData testimonialCreateData){
        this.active = true;
        this.personName = testimonialCreateData.personName();
        this.testimonialText = testimonialCreateData.testimonialText();
        this.imagePath = testimonialCreateData.imagePath();
    }

    public Testimonial() {
    }

    public Testimonial(Long id, String personName, String testimonialText, String imagePath, boolean active) {
        this.id = id;
        this.personName = personName;
        this.testimonialText = testimonialText;
        this.imagePath = imagePath;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public String getTestimonialText() {
        return testimonialText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Testimonial that = (Testimonial) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
