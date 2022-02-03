package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MovieLanguages.MovieLanguagesId.class)
public class MovieLanguages {
    @Id
    @Column(name = "movie_id")
    Long movieId;
    @Id
    @Column(name = "language_id")
    Long languageId;
    @Id
    @Column(name = "language_role_id")
    Long languageRoleId;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName="movie_id", insertable = false, updatable = false)
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName="language_id", insertable = false, updatable = false)
    Language language;

    @ManyToOne
    @JoinColumn(name = "language_role_id", referencedColumnName="role_id", insertable = false, updatable = false)
    LanguageRole languageRole;

    public static class MovieLanguagesId implements Serializable {
        Long movieId;
        Long languageId;
        Long languageRoleId;

        public MovieLanguagesId(){}

        public MovieLanguagesId(Long movieId, Long languageId, Long languageRoleId) {
            this.movieId = movieId;
            this.languageId = languageId;
            this.languageRoleId = languageRoleId;
        }

        public Long getMovieId() {
            return movieId;
        }

        public void setMovieId(Long movieId) {
            this.movieId = movieId;
        }

        public Long getLanguageId() {
            return languageId;
        }

        public void setLanguageId(Long languageId) {
            this.languageId = languageId;
        }

        public Long getLanguageRoleId() {
            return languageRoleId;
        }

        public void setLanguageRoleId(Long languageRoleId) {
            this.languageRoleId = languageRoleId;
        }
    }


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getLanguageRoleId() {
        return languageRoleId;
    }

    public void setLanguageRoleId(Long languageRoleId) {
        this.languageRoleId = languageRoleId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LanguageRole getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(LanguageRole languageRole) {
        this.languageRole = languageRole;
    }
}
