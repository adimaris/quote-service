package com.dimaris.quoteservice.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Quote> quoteList;

    protected Author() {}

    public Author(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quote> getQuoteList() {
        return quoteList;
    }

    public void setQuoteList(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }
}
