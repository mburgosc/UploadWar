package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {
    @Id
   @Column(name = "genre_id")
    Long genreId;
    String genreName;

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(
                    name = "genre_id",
                    referencedColumnName = "genre_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            )
    )
    Set<Movie> movies;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
