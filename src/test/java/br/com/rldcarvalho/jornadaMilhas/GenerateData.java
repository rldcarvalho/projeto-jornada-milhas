package br.com.rldcarvalho.jornadaMilhas;

import br.com.rldcarvalho.jornadaMilhas.model.testmonial.Testimonial;
import net.datafaker.Faker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateData {

    private static Faker faker = new Faker();

    public static Testimonial randomTestimonial(){
        return new Testimonial(
                faker.random().nextLong(),
                faker.funnyName().name(),
                faker.lorem().sentence(),
                faker.internet().url()
        );
    }

    public static List<Testimonial> randomTestimonialList(int count){
        return Stream.generate(GenerateData::randomTestimonial).limit(count).collect(Collectors.toList());
    }
}
