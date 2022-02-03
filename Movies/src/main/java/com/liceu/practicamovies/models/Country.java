package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {
    @Id
    @Column(name = "country_id")
    Long countryId;
    String countryIsoCode;
    String countryName;

    @ManyToMany
    @JoinTable(name = "production_country",
            joinColumns = @JoinColumn(
                    name = "country_id",
                    referencedColumnName = "country_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            )
    )
    Set<Movie> movies;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
