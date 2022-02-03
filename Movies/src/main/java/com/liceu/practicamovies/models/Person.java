package com.liceu.practicamovies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Person {
    @Id
    @Column(name = "person_id")
    Long personId;
    String personName;

    @OneToMany
    @JoinColumn(name = "person_id")
    @JsonIgnore
    Set<MovieCast> movieCast;

    @OneToMany
    @JoinColumn(name = "person_id")
    @JsonIgnore
    Set<MovieCrew> movieCrew;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Set<MovieCast> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<MovieCast> movieCast) {
        this.movieCast = movieCast;
    }

    public Set<MovieCrew> getMovieCrew() {
        return movieCrew;
    }

    public void setMovieCrew(Set<MovieCrew> movieCrew) {
        this.movieCrew = movieCrew;
    }
}
