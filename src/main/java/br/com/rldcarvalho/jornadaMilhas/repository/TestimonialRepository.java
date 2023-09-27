package br.com.rldcarvalho.jornadaMilhas.repository;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

    @Query(value = "SELECT * FROM testimonial WHERE active = true ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Testimonial> findRandomTestimonials();

    @Query(value = "SELECT * FROM testimonial WHERE active = true AND id = :id", nativeQuery = true)
    Optional<Testimonial> findByIdAndActive(@Param("id") Long id);
}
