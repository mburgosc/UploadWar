package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCrewRepo extends JpaRepository<MovieCrew,Long> {
    List<MovieCrew> findAllByPersonId(Long personId);

}
