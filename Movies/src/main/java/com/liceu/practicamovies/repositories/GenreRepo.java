package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.Genre;
import com.liceu.practicamovies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre,Long> {

    List<Genre> findAllByGenreNameContaining(String term);

    @Query("select g from Genre g INNER JOIN g.movies m where m.movieId = :movieId group by g.genreId")
    Genre findByMovieId(Long movieId);
}
