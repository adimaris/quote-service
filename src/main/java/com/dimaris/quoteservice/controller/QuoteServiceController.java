package com.dimaris.quoteservice.controller;

import com.dimaris.quoteservice.exceptions.AuthorNotFoundException;
import com.dimaris.quoteservice.models.Author;
import com.dimaris.quoteservice.repos.AuthorRepository;
import com.dimaris.quoteservice.repos.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("quoteservice/v1/")
public class QuoteServiceController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/authors")
    public List<Author> retrieveAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    public EntityModel<Author> retrieveAuthorById(@PathVariable int id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author == null) {
            throw new AuthorNotFoundException("id = " + id);
        }

        EntityModel<Author> resource = EntityModel.of(author.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllAuthors());
        resource.add(linkTo.withRel("all-authors"));
        return resource;
    }


}
