package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialSimpleData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialDetailData;
import br.com.rldcarvalho.jornadaMilhas.repository.TestimonialRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/depoimentos")
public class TestimonialController {

    @Autowired
    private TestimonialRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postTestimonial(@RequestBody TestimonialSimpleData testimonialSimpleData, UriComponentsBuilder uriBuilder){

        Testimonial testimonial = repository.save(new Testimonial(testimonialSimpleData));

        URI uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(testimonial.getId()).toUri();

        TestimonialDetailData testimonialDetailData = new TestimonialDetailData(testimonial);

        return ResponseEntity.created(uri).body(testimonialDetailData);

    }

    @GetMapping
    public ResponseEntity<List<TestimonialDetailData>> getAllTestimonial(){
        return ResponseEntity.ok(repository.findAll().stream().filter(Testimonial::isActive).map(TestimonialDetailData::new).toList());
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<TestimonialDetailData> updateTestimonial(@PathVariable Long id, @RequestBody TestimonialSimpleData testimonialSimpleData){
        Optional<Testimonial> data = repository.findById(id);
        if (data.isEmpty()){
            throw new EntityNotFoundException("Testimonial id not found");
        }
        Testimonial testimonial = data.get();
        testimonial.dataUpdate(testimonialSimpleData);
        return ResponseEntity.ok().body(new TestimonialDetailData(testimonial));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTestimonial(@PathVariable Long id){
        Optional<Testimonial> data = repository.findById(id);
        if (data.isEmpty()){
            throw new EntityNotFoundException("Testimonial id not found");
        }
        Testimonial testimonial = data.get();
        testimonial.delete();

        return ResponseEntity.noContent().build();

    }

}
