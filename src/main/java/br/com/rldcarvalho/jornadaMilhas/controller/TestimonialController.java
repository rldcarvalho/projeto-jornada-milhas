package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialCreateData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialDetailData;
import br.com.rldcarvalho.jornadaMilhas.repository.TestimonialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/depoimentos")
public class TestimonialController {

    @Autowired
    private TestimonialRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postTestimonial(@RequestBody TestimonialCreateData testimonialCreateData, UriComponentsBuilder uriBuilder){

        Testimonial testimonial = repository.save(new Testimonial(testimonialCreateData));

        URI uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(testimonial.getId()).toUri();

        TestimonialDetailData testimonialDetailData = new TestimonialDetailData(testimonial);

        return ResponseEntity.created(uri).body(testimonialDetailData);

    }

    @GetMapping
    public ResponseEntity<List<TestimonialDetailData>> getAllTestimonial(){
        return ResponseEntity.ok(repository.findAll().stream().filter(testimonial -> testimonial.isActive()).map(TestimonialDetailData::new).toList());
    }
}
