package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KeywordRepo extends JpaRepository<Keyword,Long> {

    @Query("select k from Keyword k RIGHT OUTER JOIN k.movies m where m.movieId = :movieId")
    List<Keyword> findAllByMovieId(Long movieId);

    @Query("select MAX(k.keywordId) from Keyword k")
    Long getMaxId();

    Optional<Keyword> findByKeywordName(String keywordName);

    Page<Keyword> findByKeywordNameContaining(Pageable withPage, String keywordName);

    List<Keyword> findByKeywordNameContaining(String keywordName);
}
