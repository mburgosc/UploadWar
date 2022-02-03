package com.liceu.practicamovies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MovieCast.MovieCastId.class)
public class MovieCast {
    @Id
    @Column(name = "movie_id")
    Long movieId;
    @Id
    @Column(name="gender_id")
    int genderId;
    @Id
    @Column(name = "person_id")
    Long personId;

    String characterName;

    int castOrder;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName="movie_id", insertable = false, updatable = false)
    @JsonIgnore
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName="gender_id", insertable = false, updatable = false)
    @JsonIgnore
    Gender gender;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName="person_id", insertable = false, updatable = false)
    @JsonIgnore
    Person person;

    public static class MovieCastId implements Serializable {
        Long movieId;
        int genderId;
        Long personId;

        public MovieCastId(){}

        public MovieCastId(Long movieId, int genderId, Long personId) {
            this.movieId = movieId;
            this.genderId = genderId;
            this.personId = personId;
        }

        public Long getMovieId() {
            return movieId;
        }

        public void setMovieId(Long movieId) {
            this.movieId = movieId;
        }

        public int getGenderId() {
            return genderId;
        }

        public void setGenderId(int genderId) {
            this.genderId = genderId;
        }

        public Long getPersonId() {
            return personId;
        }

        public void setPersonId(Long personId) {
            this.personId = personId;
        }
    }


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCastOrder() {
        return castOrder;
    }

    public void setCastOrder(int castOrder) {
        this.castOrder = castOrder;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
