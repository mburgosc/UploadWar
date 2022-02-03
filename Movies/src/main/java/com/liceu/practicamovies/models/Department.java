package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {
    @Id
    @Column(name = "department_id")
    Long departmentId;
    String departmentName;

    @OneToMany
    @JoinColumn(name = "department_id")
    Set<MovieCrew> movieCrew;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<MovieCrew> getMovieCrew() {
        return movieCrew;
    }

    public void setMovieCrew(Set<MovieCrew> movieCrew) {
        this.movieCrew = movieCrew;
    }
}
