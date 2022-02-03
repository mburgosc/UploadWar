package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Gender {
    @Id
    @Column(name = "gender_id")
    int genderId;
    String gender;

    @OneToMany
    @JoinColumn(name = "gender_id")
    Set<MovieCast> movieCast;

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<MovieCast> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<MovieCast> movieCast) {
        this.movieCast = movieCast;
    }
}
