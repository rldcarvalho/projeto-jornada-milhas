package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.model.destination.Destination;
import br.com.rldcarvalho.jornadaMilhas.model.destination.DestinationDetailData;
import br.com.rldcarvalho.jornadaMilhas.model.destination.DestinationSimpleData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialDetailData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialSimpleData;
import br.com.rldcarvalho.jornadaMilhas.repository.DestinationRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/destinos")
public class DestinationController {

    @Autowired
    private DestinationRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postDestination(@RequestBody @Valid DestinationSimpleData destinationSimpleData, UriComponentsBuilder uriBuilder){

        Destination destination = repository.save(new Destination(destinationSimpleData));

        URI uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(destination.getId()).toUri();

        DestinationDetailData destinationDetailData = new DestinationDetailData(destination);

        return ResponseEntity.created(uri).body(destinationDetailData);

    }
}
