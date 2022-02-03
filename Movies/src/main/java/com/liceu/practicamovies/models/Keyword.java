package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Keyword {
    @Id
    @Column(name = "keyword_id")
    Long keywordId;
    String keywordName;

    @ManyToMany
    //@JoinColumn(name = "keyword_id")
    @JoinTable(name = "movie_keywords",
            joinColumns = @JoinColumn(
                    name = "keyword_id",
                    referencedColumnName = "keyword_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            )
    )
    Set<Movie> movies;

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
