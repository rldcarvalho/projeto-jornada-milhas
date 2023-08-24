package br.com.rldcarvalho.jornadaMilhas.controller;

import br.com.rldcarvalho.jornadaMilhas.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depoimentos")
public class TestimonialController {

    @Autowired
    private TestimonialRepository repository;
}
