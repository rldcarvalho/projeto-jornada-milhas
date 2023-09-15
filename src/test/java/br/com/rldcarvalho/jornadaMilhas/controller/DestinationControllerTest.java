package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.GenerateData;
import br.com.rldcarvalho.jornadaMilhas.model.destination.Destination;
import br.com.rldcarvalho.jornadaMilhas.model.destination.DestinationDetailData;
import br.com.rldcarvalho.jornadaMilhas.model.destination.DestinationSimpleData;
import br.com.rldcarvalho.jornadaMilhas.repository.DestinationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DestinationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DestinationDetailData> destinationDetailData;

    @Autowired
    private JacksonTester<List<DestinationDetailData>> destinationDetailDataList;

    @Autowired
    private JacksonTester<DestinationSimpleData> destinationSimpleData;

    @MockBean
    private DestinationRepository repository;

    @Test
    @DisplayName("Should return HTTP status 201 when all submitted data is valid")
    void testPostDestinationCodeCreated() throws Exception {
        Destination destination = GenerateData.randomDestination();
        DestinationSimpleData data = new DestinationSimpleData(
                destination.getName(),
                destination.getPhotoPath(),
                destination.getPrice()
        );
        String json = destinationSimpleData.write(data).getJson();

        Mockito.when(repository.save(any())).thenReturn(destination);

        MockHttpServletResponse response = mockMvc.perform(post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should return HTTP status 400 when submitted data is invalid")
    void testPostDestinationBadRequest() throws Exception {
        DestinationSimpleData data = new DestinationSimpleData(null, null, null);
        String json = destinationSimpleData.write(data).getJson();

        MockHttpServletResponse response = mockMvc.perform(post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return JSON with a list of Destinations and status 200")
    void testGetAllDestinations() throws Exception {
        List<Destination> destinations = GenerateData.randomDestinationList(5);

        Mockito.when(repository.findAllFilteredByName(any())).thenReturn(destinations);

        MockHttpServletResponse response = mockMvc.perform(get("/destinos")).andReturn().getResponse();

        List<DestinationDetailData> data = destinations.stream().map(DestinationDetailData::new).collect(Collectors.toList());
        String json = destinationDetailDataList.write(data).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return JSON with the searched destination and status 200")
    void testGetOneDestination() throws Exception {
        List<Destination> destinations = GenerateData.randomDestinationList(5);
        Destination destinationToFind = destinations.get(2);
        String expectedJson = destinationDetailDataList.write(Collections.singletonList(new DestinationDetailData(destinationToFind))).getJson();

        Mockito.when(repository.findAllFilteredByName(destinationToFind.getName())).thenReturn(Collections.singletonList(destinationToFind));

        MockHttpServletResponse response = mockMvc.perform(get("/destinos").param("name", destinationToFind.getName())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    @DisplayName("Should return NOT FOUND status when the searched parameter is not found")
    void testGetAllStatusEmptyContent() throws Exception {
        List<Destination> data = Collections.emptyList();
        String nameFilter = "filter";

        Mockito.when(repository.findAllFilteredByName(any())).thenReturn(data);

        MockHttpServletResponse response = mockMvc
                .perform(get("/destinos").param("name", nameFilter))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Should return OK status and the updated Destination when the request body is valid")
    void testUpdateStatusCodeOK() throws Exception {
        Destination destination = GenerateData.randomDestination();
        Destination updatedDestination = GenerateData.randomDestination();

        DestinationSimpleData data = new DestinationSimpleData(
                updatedDestination.getName(),
                updatedDestination.getPhotoPath(),
                updatedDestination.getPrice()
        );
        String requestJson = destinationSimpleData.write(data).getJson();

        DestinationDetailData updatedData = new DestinationDetailData(
                destination.getId(),
                updatedDestination.getName(),
                updatedDestination.getPhotoPath(),
                updatedDestination.getPrice()
        );
        String responseJson = destinationDetailData.write(updatedData).getJson();

        Mockito.when(repository.findByIdAndActive(destination.getId())).thenReturn(Optional.of(destination));

        MockHttpServletResponse response = mockMvc
                .perform(put("/destinos/{id}", destination.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }

    @Test
    @DisplayName("Should return BAD REQUEST status when submitted data is invalid")
    void testUpdateStatusCodeBadRequest() throws Exception {
        DestinationSimpleData data = new DestinationSimpleData(null, null, null);
        String json = destinationSimpleData.write(data).getJson();

        Destination destination = GenerateData.randomDestination();
        Mockito.when(repository.findByIdAndActive(any())).thenReturn(Optional.of(destination));

        MockHttpServletResponse response = mockMvc
                .perform(put("/destinos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return NO CONTENT status when the chosen ID in the path is found")
    void testDeleteStatusCodeNoContent() throws Exception {
        Destination destination = GenerateData.randomDestination();

        Mockito.when(repository.findByIdAndActive(any())).thenReturn(Optional.of(destination));

        MockHttpServletResponse response = mockMvc
                .perform(delete("/destinos/{id}", destination.getId()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(destination.isActive()).isFalse();
    }

    @Test
    @DisplayName("Should return 404 when the Destination ID is not found")
    void testDeleteNonExistentEntity() throws Exception {
        Long idToDelete = 2L;

        Mockito.when(repository.findByIdAndActive(idToDelete)).thenReturn(Optional.empty());

        mockMvc
                .perform(delete("/destinos/{id}", idToDelete))
                .andExpect(status().isNotFound());
    }

}