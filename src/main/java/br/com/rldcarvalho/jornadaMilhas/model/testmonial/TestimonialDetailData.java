package br.com.rldcarvalho.jornadaMilhas.model.testmonial;

public record TestimonialDetailData(
        Long id,
        String personName,
        String testimonialText,
        String imagePath
) {
    public TestimonialDetailData(Testimonial testimonial){
        this(testimonial.getId(), testimonial.getPersonName(), testimonial.getTestimonialText(), testimonial.getImagePath());
    }
}
