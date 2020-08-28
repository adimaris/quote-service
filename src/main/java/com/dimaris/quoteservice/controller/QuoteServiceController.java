package com.dimaris.quoteservice.controller;

import com.dimaris.quoteservice.exceptions.AuthorNotFoundException;
import com.dimaris.quoteservice.models.Author;
import com.dimaris.quoteservice.models.Quote;
import com.dimaris.quoteservice.repos.AuthorRepository;
import com.dimaris.quoteservice.repos.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
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

        if (!author.isPresent()) {
            throw new AuthorNotFoundException("id = " + id);
        }

        EntityModel<Author> resource = EntityModel.of(author.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllAuthors());
        resource.add(linkTo.withRel("all-authors"));
        return resource;
    }

    @PostMapping("/authors")
    public ResponseEntity<Object> createUser(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAuthor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/authors/{id}/quotes")
    public List<Quote> retrieveAuthorQuotes(@PathVariable int id) {
        Optional<Author> author = authorRepository.findById(id);

        if (!author.isPresent()) {
            throw new AuthorNotFoundException("id = " + id);
        }

        return author.get().getQuoteList();
    }

    @PostMapping("/authors/{id}/quotes")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Quote quote) {

        Optional<Author> authorOptional = authorRepository.findById(id);

        if (!authorOptional.isPresent()) {
            throw new AuthorNotFoundException("id-" + id);
        }

        Author author = authorOptional.get();
        quote.setAuthor(author);
        quoteRepository.save(quote);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(quote.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/authors/{id}/quotes/{quoteId}")
    public void deleteUser(@PathVariable int id, @PathVariable int quoteId) {
        Optional<Author> author = authorRepository.findById(id);

        if (!author.isPresent()) {
            throw new AuthorNotFoundException("id = " + id);
        }

        quoteRepository.deleteById(quoteId);
    }
}
