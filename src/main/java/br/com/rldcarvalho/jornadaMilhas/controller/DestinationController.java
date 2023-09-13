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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DestinationDetailData> getDestinationById(@PathVariable Long id) {
        Optional<Destination> destination = repository.findByIdAndActive(id);

        return destination.map(value -> ResponseEntity.ok().body(new DestinationDetailData(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DestinationDetailData>> getDestinations(@RequestParam(name="name", required = false) String name) {
        List<Destination> data = repository.findAllFilteredByName(name);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(data.stream().map(DestinationDetailData::new).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DestinationDetailData> updateDestination(@PathVariable Long id, @RequestBody @Valid DestinationSimpleData destinationSimpleData) {
        Optional<Destination> data = repository.findByIdAndActive(id);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Destination destination = data.get();

        destination.dataUpdate(destinationSimpleData);

        return ResponseEntity.ok().body(new DestinationDetailData(destination));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Destination> data = repository.findByIdAndActive(id);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        data.get().delete();

        return ResponseEntity.noContent().build();
    }
}
