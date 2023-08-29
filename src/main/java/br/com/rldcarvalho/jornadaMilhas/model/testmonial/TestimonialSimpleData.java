package br.com.rldcarvalho.jornadaMilhas.model.testmonial;

import jakarta.validation.constraints.NotBlank;

public record TestimonialSimpleData(
        @NotBlank
        String personName,
        @NotBlank
        String testimonialText,
        String imagePath
) {}
