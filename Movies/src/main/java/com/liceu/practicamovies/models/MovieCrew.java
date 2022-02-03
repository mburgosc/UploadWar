package com.liceu.practicamovies.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MovieCrew.MovieCrewId.class)
public class MovieCrew {
    @Id
    @Column(name = "movie_id")
    Long movieId;
    @Id
    @Column(name = "person_id")
    Long personId;
    @Id
    @Column(name = "department_id")
    Long departmentId;
    @Id
    String job;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName="movie_id", insertable = false, updatable = false)
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName="person_id", insertable = false, updatable = false)
    Person person;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName="department_id", insertable = false, updatable = false)
    Department department;

    public static class MovieCrewId implements Serializable {

        Long movieId;
        Long personId;
        Long departmentId;
        String job;
        public MovieCrewId(){}

        public MovieCrewId(Long movieId, Long personId, Long departmentId, String job) {
            this.movieId = movieId;
            this.personId = personId;
            this.departmentId = departmentId;
            this.job = job;
        }

        public Long getMovieId() {
            return movieId;
        }

        public void setMovieId(Long movieId) {
            this.movieId = movieId;
        }

        public Long getPersonId() {
            return personId;
        }

        public void setPersonId(Long personId) {
            this.personId = personId;
        }

        public Long getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
