package br.com.rldcarvalho.jornadaMilhas.model.testmonial;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Simple data for a testimonial")
public record TestimonialSimpleData(
        @Schema(description = "Name of the person providing the testimonial", required = true)
        @NotBlank
        String personName,

        @Schema(description = "Text of the testimonial", required = true)
        @NotBlank
        String testimonialText,

        @Schema(description = "Path of the image for the testimonial")
        String imagePath
) {}
