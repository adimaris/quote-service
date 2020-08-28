package com.dimaris.quoteservice;

import com.dimaris.quoteservice.models.Author;
import com.dimaris.quoteservice.models.Quote;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuoteServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "quoteservice/v1/";
    }

    @Test
    public void testGetAllAuthors() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/authors", HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAuthorById() {
        Author author = restTemplate.getForObject(getRootUrl() + "/authors/1", Author.class);
        Assert.assertNotNull(author.getName());
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author(1001, "Jane Austen");
        ResponseEntity<Author> postResponse = restTemplate.postForEntity(getRootUrl() + "/authors", author, Author.class);
        Assert.assertNotNull(postResponse);
    }

    @Test
    public void testGetAllQuotesByAuthor() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        int id = 2;
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/authors/" + id + "/quotes", HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testCreateQuote() {
        int authorId = 2;
        int quoteId = 1000;

        Quote quote = new Quote(quoteId, "THis is a test quote");
        ResponseEntity<Quote> postResponse = restTemplate.postForEntity(getRootUrl() + "/authors" + authorId + "/quotes", quote, Quote.class);
        Assert.assertNotNull(postResponse);
    }

    @Test
    public void testDeleteQuote() {

        int authorId = 2;
        int quoteId = 6;

        Quote quote = restTemplate.getForObject(getRootUrl() + "/authors/" + authorId + "/quotes/" + quoteId, Quote.class);
        Assert.assertNotNull(quote);
        restTemplate.delete(getRootUrl() + "/authors/" + authorId + "quotes" + quoteId);

        try {
            quote = restTemplate.getForObject(getRootUrl() + "/authors/" + authorId + "/quotes/" + quoteId, Quote.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
