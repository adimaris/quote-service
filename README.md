# Quote Service
A REST API created using Spring Boot that allows the user to access quotes mapped to famous auhtors.

# Access
- The REST API is deployed using AWS Elastic Bean Stalk. All authors can be retrieved at [this endpoint](http://quoteservice-env.eba-th4anqyj.us-east-2.elasticbeanstalk.com/quoteservice/v1/authors). 
- The Swagger documentation is available at [this link](http://quoteservice-env.eba-th4anqyj.us-east-2.elasticbeanstalk.com/swagger-ui/#).

# Commands
The REST API supports commands to:
- GET all authors using /quoteservice/v1/authors
- GET author by id using /quoteservice/v1/authors/{id}
- POST an author using /quoteservice/v1/authors
- GET all quotes by an author using their author id at /quoteservice/v1/authors/{id}/quotes
- POST a new quote to a specific author /quoteservice/v1/authors/{id}/quotes
- DELETE a quote mapped to a specific author /quoteservice/v1/authors/{id}/quotes/{quoteId}

# Default Quotes
The API is seeded with quotes from:
- David Foster Wallace
- Jonathan Franzen
- James Baldwin
- Toni Morrison
- Margaret Atwood
- Haruki Murakami
- Virginia Woolf
- Gabriel Garcia Marquez
- Franz Kafka
- Albert Camus
