package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LanguageRole {
    @Id
    @Column(name = "role_id", unique = true)
    Long roleId;
    String languageRole;

    @OneToMany
    @JoinColumn(name = "language_role_id")
    Set<MovieLanguages> movieLanguages;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(String languageRole) {
        this.languageRole = languageRole;
    }

    public Set<MovieLanguages> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<MovieLanguages> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }
}
