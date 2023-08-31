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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TestimonialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TestimonialDetailData> testimonialDetailData;

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
    void testPostTestinomialBadRequest() throws Exception {
        TestimonialSimpleData data = new TestimonialSimpleData(null, null,null);
        String json = testimonialSimpleData.write(data).getJson();

        MockHttpServletResponse response = mockMvc.perform(post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}