# Quote Service
A REST API created using Spring Boot that allows the user to access quotes mapped to famous auhtors.

# Access
- The REST API is hosted on an AWS EC2 instance and can be accessed at [this link](http://ec2-3-131-169-83.us-east-2.compute.amazonaws.com:8080/quoteservice/v1/authors/). 
- Access the Swagger documentation at [this link](http://ec2-3-131-169-83.us-east-2.compute.amazonaws.com:8080/swagger-ui/#).

# Command
The REST API supports commands to:
- GET all authors
- GET author by id
- POST an author
- GET all quotes by an author
- POST a new quote to a specific author
- DELTE a quote mapped to a specific author

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
