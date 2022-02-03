package com.liceu.practicamovies.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Language {
    @Id
            @Column(name = "language_id")
    Long languageId;
    String languageCode;
    String languageName;

    @OneToMany
    @JoinColumn(name = "language_id")
    Set<MovieLanguages> movieLanguages;

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Set<MovieLanguages> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<MovieLanguages> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }
}
