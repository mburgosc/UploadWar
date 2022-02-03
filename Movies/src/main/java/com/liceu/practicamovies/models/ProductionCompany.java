package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductionCompany {
    @Id
    @Column(name = "company_id")
    Long companyId;
    String companyName;

    @ManyToMany
    @JoinTable(name = "movie_company",
            joinColumns = @JoinColumn(
                    name = "company_id",
                    referencedColumnName = "company_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            )
    )
    Set<Movie> movies;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
