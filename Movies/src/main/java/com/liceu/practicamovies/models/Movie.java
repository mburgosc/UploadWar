package com.liceu.practicamovies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @Column(name="movie_id")
    Long movieId;
    String title;
    Long budget;
    String homepage;
    String overview;
    double popularity;
    Date releaseDate;
    BigInteger revenue;
    int runtime;
    String movieStatus;
    String tagline;
    double voteAverage;
     int voteCount;

    @ManyToMany
    @JoinTable(name = "production_country",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "country_id",
                    referencedColumnName = "country_id"
            )
    )
    @JsonIgnore
    Set<Country> countries;

    @OneToMany
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    Set<MovieCast> movieCast;

    @ManyToMany
    @JoinTable(name = "movie_company",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "company_id",
                    referencedColumnName = "company_id"
            )
    )
    @JsonIgnore
    Set<ProductionCompany> companies;

    @OneToMany
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    Set<MovieCrew> movieCrew;

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id",
                    referencedColumnName = "genre_id"
            )
    )
    @JsonIgnore
    Set<Genre> genres;

    @ManyToMany
    @JoinTable(name = "movie_keywords",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movie_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "keyword_id",
                    referencedColumnName = "keyword_id"
            )
    )
    @JsonIgnore
    Set<Keyword> keywords;

    @OneToMany
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    Set<MovieLanguages> movieLanguages;



    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigInteger getRevenue() {
        return revenue;
    }

    public void setRevenue(BigInteger revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public Set<MovieCast> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<MovieCast> movieCast) {
        this.movieCast = movieCast;
    }

    public Set<ProductionCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<ProductionCompany> companies) {
        this.companies = companies;
    }

    public Set<MovieCrew> getMovieCrew() {
        return movieCrew;
    }

    public void setMovieCrew(Set<MovieCrew> movieCrew) {
        this.movieCrew = movieCrew;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Set<MovieLanguages> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<MovieLanguages> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }
}
