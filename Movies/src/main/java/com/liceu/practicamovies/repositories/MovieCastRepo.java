package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.MovieCast;
import com.liceu.practicamovies.models.MovieCast.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieCastRepo extends JpaRepository<MovieCast, MovieCastId> {


    //@Query("select mc.characterName from MovieCast mc where mc.characterName like %:term% group by mc.characterName")
    List<MovieCast> findAllByCharacterNameContaining(String term);

    List<MovieCast> findAllByPersonId(Long personId);

    @Query("select MAX(mc.castOrder) from MovieCast mc")
    Long getMaxCastOrder();

    @Override
    void deleteAll(Iterable<? extends MovieCast> entities);
}
