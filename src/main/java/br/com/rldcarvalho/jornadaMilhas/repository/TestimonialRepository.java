package br.com.rldcarvalho.jornadaMilhas.repository;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
}
