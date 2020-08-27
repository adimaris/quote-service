package com.dimaris.quoteservice.repos;

import com.dimaris.quoteservice.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

}
