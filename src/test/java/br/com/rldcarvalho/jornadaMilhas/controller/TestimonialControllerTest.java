package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.GenerateData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialDetailData;
import br.com.rldcarvalho.jornadaMilhas.model.testmonial.TestimonialSimpleData;
import br.com.rldcarvalho.jornadaMilhas.repository.TestimonialRepository;
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
class TestimonialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TestimonialDetailData> testimonialDetailData;

    @Autowired
    private JacksonTester<List<TestimonialDetailData>> testimonialDetailDataList;

    @Autowired
    private JacksonTester<TestimonialSimpleData> testimonialSimpleData;

    @MockBean
    private TestimonialRepository repository;

    @Test
    @DisplayName("Should return 201 http code when all data submitted are valid")
    void testPostTestimonialCodeCreated() throws Exception {
        Testimonial testimonial = GenerateData.randomTestimonial();
        TestimonialSimpleData data = new TestimonialSimpleData(
                testimonial.getPersonName(),
                testimonial.getTestimonialText(),
                testimonial.getImagePath()
        );
        String json = testimonialSimpleData.write(data).getJson();

        Mockito.when(repository.save(any())).thenReturn(testimonial);

        MockHttpServletResponse response = mockMvc.perform(post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should return 400 http code when data submitted are invalid")
    void testPostTestimonialBadRequest() throws Exception {
        TestimonialSimpleData data = new TestimonialSimpleData(null, null,null);
        String json = testimonialSimpleData.write(data).getJson();

        MockHttpServletResponse response = mockMvc.perform(post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return json with a list of Testimonials and status 200")
    void testGetAllTestimonial() throws Exception {
        List<Testimonial> testimonials = GenerateData.randomTestimonialList(5);

        Mockito.when(repository.findAll()).thenReturn(testimonials);

        MockHttpServletResponse response = mockMvc.perform(get("/depoimentos")).andReturn().getResponse();

        List<TestimonialDetailData> data = testimonials.stream().map(TestimonialDetailData::new).collect(Collectors.toList());
        String json = testimonialDetailDataList.write(data).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Should return empty list of TestimonialDetailRecord and status OK")
    void testGetAllStatusEmptyContent() throws Exception {
        List<Testimonial> data = Collections.<Testimonial>emptyList();

        Mockito.when(repository.findAll()).thenReturn(data);

        MockHttpServletResponse response = mockMvc
                .perform(get("/depoimentos"))
                .andReturn().getResponse();

        List<TestimonialDetailData> record = Collections.<TestimonialDetailData>emptyList();
        String json = testimonialDetailDataList.write(record).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(json);
    }

    @Test
    @DisplayName("Should return status OK even when there are no testimonials registered in the database")
    void getRandomTestimonials() throws Exception
    {
        MockHttpServletResponse response = mockMvc.perform(get("/depoimentos-home"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return JSON with the searched destination and status OK")
    void testGetTestimonialById() throws Exception {
        List<Testimonial> testimonials = GenerateData.randomTestimonialList(5);
        Testimonial testimonialToFind = testimonials.get(2);
        String expectedJson = testimonialDetailData.write(new TestimonialDetailData(testimonialToFind)).getJson();

        Mockito.when(repository.findByIdAndActive(testimonialToFind.getId())).thenReturn(Optional.of(testimonialToFind));

        MockHttpServletResponse response = mockMvc.perform(get("/depoimentos/{id}", testimonialToFind.getId()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    @DisplayName("Should return NOT FOUND status when the searched id is not found")
    void testGetByIdStatusEmptyContent() throws Exception {
        Mockito.when(repository.findByIdAndActive(any())).thenReturn(Optional.empty());

        MockHttpServletResponse response = mockMvc
                .perform(get("/depoimentos/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Should return status OK and updated TestimonialDetailData when request body is valid")
    void testUpdateStatusCodeOK() throws Exception {
        Testimonial testimonial = GenerateData.randomTestimonial();
        Testimonial updatedTestimonial = GenerateData.randomTestimonial();

        TestimonialSimpleData data = new TestimonialSimpleData(updatedTestimonial.getPersonName(),
                updatedTestimonial.getTestimonialText(), updatedTestimonial.getImagePath());
        String requestJson = testimonialSimpleData.write(data).getJson();

        TestimonialDetailData updatedData = new TestimonialDetailData(testimonial.getId(), updatedTestimonial.getPersonName(),
                updatedTestimonial.getTestimonialText(), updatedTestimonial.getImagePath()
        );
        String responseJson = testimonialDetailData.write(updatedData).getJson();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(testimonial));

        MockHttpServletResponse response = mockMvc
                .perform(put("/depoimentos/{id}", testimonial.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseJson);

    }
    @Test
    @DisplayName("Should return status BAD REQUEST when data submitted are invalid")
    void testUpdateStatusCodeBadRequest() throws Exception {
        TestimonialSimpleData data = new TestimonialSimpleData(null, null,  null);
        String json = testimonialSimpleData.write(data).getJson();

        Testimonial testimonial = GenerateData.randomTestimonial();
        Mockito.when(repository.getReferenceById(any())).thenReturn(testimonial);

        MockHttpServletResponse response = mockMvc
                .perform(put("/depoimentos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Should return status NO CONTENT when id chosen in the path is found")
    void testDeleteStatusCodeNoContent() throws Exception {

        Testimonial testimonial = GenerateData.randomTestimonial();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(testimonial));

        MockHttpServletResponse response = mockMvc
                .perform(delete("/depoimentos/{id}", testimonial.getId()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(testimonial.isActive()).isFalse();
    }

    @Test
    @DisplayName("Should return 404 when testimonial ID is not found")
    void testDeleteNonExistentEntity() throws Exception {

        Long idToDelete = 2L;

        Mockito.when(repository.findById(idToDelete)).thenReturn(Optional.empty());

        mockMvc
                .perform(delete("/depoimentos/{id}", idToDelete))
                .andExpect(status().isNotFound());
    }

}