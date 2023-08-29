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

    public Testimonial(TestimonialSimpleData testimonialSimpleData){
        this.active = true;
        this.personName = testimonialSimpleData.personName();
        this.testimonialText = testimonialSimpleData.testimonialText();
        this.imagePath = testimonialSimpleData.imagePath();
    }

    public Testimonial() {
    }

    public Testimonial(Long id, String personName, String testimonialText, String imagePath) {
        this.id = id;
        this.personName = personName;
        this.testimonialText = testimonialText;
        this.imagePath = imagePath;
        this.active = true;
    }

    public void dataUpdate(TestimonialSimpleData testimonialSimpleData) {
        this.personName = testimonialSimpleData.personName();
        this.testimonialText = testimonialSimpleData.testimonialText();
        this.imagePath = testimonialSimpleData.imagePath();
    }

    public void delete(){
        this.active = false;
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
