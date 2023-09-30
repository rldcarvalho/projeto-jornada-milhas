package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialDetailData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialSimpleData;
import br.com.rldcarvalho.jornadaMilhas.repository.TestimonialRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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

@RestController
@RequestMapping
@Tag(name = "TestimonialController", description = "Endpoint for operations related to testimonials")
public class TestimonialController {

    @Autowired
    private TestimonialRepository repository;

    @PostMapping(value = "/depoimentos")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new testimonial", method = "POST", description = "Create a new testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Testimonial created successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TestimonialDetailData> postTestimonial(
            @Parameter(description = "Testimonial data to create")
            @RequestBody @Valid TestimonialSimpleData testimonialSimpleData, UriComponentsBuilder uriBuilder){

        Testimonial testimonial = repository.save(new Testimonial(testimonialSimpleData));

        URI uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(testimonial.getId()).toUri();

        TestimonialDetailData testimonialDetailData = new TestimonialDetailData(testimonial);

        return ResponseEntity.created(uri).body(testimonialDetailData);

    }

    @GetMapping(value = "/depoimentos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all testimonials", method = "GET", description = "Get all testimonials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonials found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TestimonialDetailData.class)))),
            @ApiResponse(responseCode = "404", description = "No testimonials found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TestimonialDetailData>> getAllTestimonial(){
        return ResponseEntity.ok(repository.findAll().stream().filter(Testimonial::isActive).map(TestimonialDetailData::new).toList());
    }

    @GetMapping(value = "/depoimentos-home")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get random testimonials", method = "GET", description = "Get a list of 3 random testimonials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random testimonials found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TestimonialDetailData.class)))),
            @ApiResponse(responseCode = "404", description = "No random testimonials found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TestimonialDetailData>> getRandomTestimonial(){
        return ResponseEntity.ok(repository.findRandomTestimonials().stream().map(TestimonialDetailData::new).toList());
    }

    @GetMapping(value = "/depoimentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a testimonial by ID", method = "GET", description = "Get a testimonial by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonial found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialDetailData.class))),
            @ApiResponse(responseCode = "404", description = "Testimonial not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TestimonialDetailData> getTestimonialById(@Parameter(description = "Testimonial ID") @PathVariable Long id){
        Optional<Testimonial> testimonial = repository.findByIdAndActive(id);

        return testimonial.map(value -> ResponseEntity.ok().body(new TestimonialDetailData(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/depoimentos/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a testimonial by ID", method = "PUT", description = "Update a testimonial by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonial updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialDetailData.class))),
            @ApiResponse(responseCode = "404", description = "Testimonial not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TestimonialDetailData> updateTestimonial(
            @Parameter(description = "Testimonial ID to update") @PathVariable Long id,
            @Parameter(description = "Updated testimonial data") @RequestBody @Valid TestimonialSimpleData testimonialSimpleData){

        Optional<Testimonial> data = repository.findById(id);
        if (data.isEmpty()){
            throw new EntityNotFoundException("Testimonial id not found");
        }
        Testimonial testimonial = data.get();
        testimonial.dataUpdate(testimonialSimpleData);
        return ResponseEntity.ok().body(new TestimonialDetailData(testimonial));
    }

    @DeleteMapping(value = "/depoimentos/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a testimonial by ID", method = "DELETE", description = "Delete a testimonial by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Testimonial deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Testimonial not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTestimonial(@Parameter(description = "Testimonial ID to delete") @PathVariable Long id){
        Optional<Testimonial> data = repository.findById(id);
        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Testimonial testimonial = data.get();
        testimonial.delete();

        return ResponseEntity.noContent().build();

    }

}
