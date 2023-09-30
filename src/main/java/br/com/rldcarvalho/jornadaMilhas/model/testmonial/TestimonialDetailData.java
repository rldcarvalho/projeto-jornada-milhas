package br.com.rldcarvalho.jornadaMilhas.model.testmonial;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detailed data for a testimonial")
public record TestimonialDetailData(
        @Schema(description = "ID of the testimonial", required = true)
        Long id,

        @Schema(description = "Name of the person providing the testimonial", required = true)
        String personName,

        @Schema(description = "Text of the testimonial", required = true)
        String testimonialText,

        @Schema(description = "Path of the image for the testimonial")
        String imagePath
) {
    public TestimonialDetailData(Testimonial testimonial){
        this(testimonial.getId(), testimonial.getPersonName(), testimonial.getTestimonialText(), testimonial.getImagePath());
    }
}
