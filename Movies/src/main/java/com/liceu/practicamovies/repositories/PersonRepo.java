package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person,Long> {

    Person getByPersonIdAndPersonNameContaining(Long personId, String name);
    @Query("select p from Person p RIGHT OUTER JOIN p.movieCast mc WHERE p.personName like :name% and mc.personId is not null group by p.personName")
    List<Person> findAllActorsByPersonNameContaining(String name);

    @Query("select p from Person p RIGHT OUTER JOIN p.movieCast mc WHERE p.personName = :name and mc.personId is not null group by p.personName")
    Page<Person> findAllActorsByPersonName(Pageable pageable,String name);

    Person findByPersonNameContaining(String name);

    @Query("select p from Person p RIGHT OUTER JOIN p.movieCrew mc WHERE p.personName like :name% and mc.job = 'Director' group by p.personName")
    List<Person> findAllDirectorsByPersonNameContaining(String name);

    @Query("select p from Person p RIGHT OUTER JOIN p.movieCast WHERE p.personName = :actorName group by p.personName")
    Person findActorByName(String actorName);

    @Query("select MAX(p.personId) from Person p")
    Long findMaxId();

    Person findByPersonName(String actorName);

    @Query("select p from Person p RIGHT OUTER JOIN p.movieCast mc WHERE mc.personId is not null group by p.personName")
    Page<Person> findAllActors(Pageable pageable);

    @Query("select p from Person p RIGHT OUTER JOIN p.movieCast mc WHERE mc.personId is not null group by p.personName")
    List<Person> findAllActors();

    @Query("select p from Person p RIGHT OUTER JOIN p.movieCast mc WHERE p.personId = :personId and mc.personId is not null")
    Person findActorBypersonId(Long personId);
}
