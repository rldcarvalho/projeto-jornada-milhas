package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.model.destination.Destination;
import br.com.rldcarvalho.jornadaMilhas.model.destination.DestinationDetailData;
import br.com.rldcarvalho.jornadaMilhas.model.destination.DestinationSimpleData;
import br.com.rldcarvalho.jornadaMilhas.repository.DestinationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/destinos")
@Tag(name = "DestinationController", description = "Endpoint for operations related to destinations")
public class DestinationController {

    @Autowired
    private DestinationRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new destination", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Destination created successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DestinationDetailData> postDestination(@Parameter(description = "Destination data to create") @RequestBody @Valid DestinationSimpleData destinationSimpleData, UriComponentsBuilder uriBuilder){

        Destination destination = repository.save(new Destination(destinationSimpleData));

        URI uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(destination.getId()).toUri();

        DestinationDetailData destinationDetailData = new DestinationDetailData(destination);

        return ResponseEntity.created(uri).body(destinationDetailData);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a destination by ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destination found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationDetailData.class))),
            @ApiResponse(responseCode = "404", description = "Destination not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DestinationDetailData> getDestinationById(@Parameter(description = "Destination ID") @PathVariable Long id) {
        Optional<Destination> destination = repository.findByIdAndActive(id);

        return destination.map(value -> ResponseEntity.ok().body(new DestinationDetailData(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get destinations", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinations found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DestinationDetailData.class)))),
            @ApiResponse(responseCode = "404", description = "No destinations found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<DestinationDetailData>> getDestinations(
            @Parameter(description = "Filter destinations by name (optional)")
            @RequestParam(name="name", required = false) String name) {

        List<Destination> data = repository.findAllFilteredByName(name);

        if (data.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum destino foi encontrado", null);
        }

        return ResponseEntity.ok().body(data.stream().map(DestinationDetailData::new).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a destination by ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destination updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationDetailData.class))),
            @ApiResponse(responseCode = "404", description = "Destination not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DestinationDetailData> updateDestination(
            @PathVariable Long id,
            @Parameter(description = "Updated destination data") @RequestBody @Valid DestinationSimpleData destinationSimpleData) {

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
    @Operation(summary = "Delete a destination by ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Destination deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Destination not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Destination ID to delete") @PathVariable Long id) {
        Optional<Destination> data = repository.findByIdAndActive(id);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        data.get().delete();

        return ResponseEntity.noContent().build();
    }
}
