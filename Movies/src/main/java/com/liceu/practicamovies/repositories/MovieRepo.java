package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.Keyword;
import com.liceu.practicamovies.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie,Long> {

    Page<Movie> findAllByTitleContaining(Pageable pageable, String title);

    List<Movie> findAllByTitleContaining(String title);


    Page<Movie> findAllByMovieCast_PersonPersonName(Pageable pageable,String actorName);


    Page<Movie> findAllByMovieCastCharacterName(Pageable pageable,String characterName);


    Page<Movie> findAllByGenres(Pageable pageable,String genreName);

    @Query("select m from Movie m RIGHT OUTER JOIN m.movieCrew mc LEFT OUTER JOIN mc.person p where p.personName = :directorName and job = 'Director'")
    Page<Movie> findAllByDirector(Pageable pageable,String directorName);



    List<Movie> findAllByTitle(String titleMovie);

    Movie findByTitle(String titleMovie);
}
