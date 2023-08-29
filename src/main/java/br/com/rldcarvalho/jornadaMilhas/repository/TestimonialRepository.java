package br.com.rldcarvalho.jornadaMilhas.repository;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

    @Query(value = "SELECT * FROM testimonial WHERE active = true ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Testimonial> findRandomTestimonials();
}
