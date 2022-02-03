package com.liceu.practicamovies.services;

import com.liceu.practicamovies.models.Movie;
import com.liceu.practicamovies.models.MovieCast;
import com.liceu.practicamovies.models.MovieCrew;
import com.liceu.practicamovies.models.Person;
import com.liceu.practicamovies.repositories.MovieCastRepo;
import com.liceu.practicamovies.repositories.MovieCrewRepo;
import com.liceu.practicamovies.repositories.MovieRepo;
import com.liceu.practicamovies.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    MovieCastRepo movieCastRepo;

    @Autowired
    MovieCrewRepo movieCrewRepo;

    @Autowired
    MovieService movieService;

    public List<Person> findAllActorsByName(String name){
        return personRepo.findAllActorsByPersonNameContaining(name);
    }

    public Page<Person> findAllActorsByName(String name, Integer size, Integer index) {

        Page<Person> actors = personRepo.findAllActorsByPersonName  (Pageable.ofSize(size).withPage(index /size),name);

        return actors;
    }
    public List<Person> findAllDirectorsByName(String name){
        return personRepo.findAllDirectorsByPersonNameContaining(name);
    }


    public Person findActorByName(String actorName) {
        return personRepo.findByPersonNameContaining(actorName);
    }

    public Person save(String personName) {
        Person actor = new Person();
        actor.setPersonName(personName);
        Long maxId = findMaxId();
        actor.setPersonId(generateRandomId());

        /*
        * EL codigo dentro de este if añadira el actor a la pelicula "none"
        * de manera que si el usuario no añade este actor a ninguna pelicula
        * pueda luego buscar al actor que ha creado en la tabla de la pagina /actors
        * ya que en esta pagina solo se busca a las personas que han actuado en una pelicula
        * */

        if (movieRepo.findByTitle("none") == null){
            Movie movie = new Movie();
            movie.setTitle("none");
            movie.setMovieId(movieService.generateRandomId());
            movieRepo.save(movie);
        }
        personRepo.save(actor);
        movieService.addMovieCast(actor.getPersonId(),"none","none",0);
        return actor;
    }

    private Long findMaxId() {
        return personRepo.findMaxId();
    }

    public void deleteActorCastsAndCrew(Long id){
        Optional<Person> actor = personRepo.findById(id);
        if (actor.isPresent()){
            List<MovieCast> movieCasts = movieCastRepo.findAllByPersonId(id);
            List<MovieCrew> movieCrews = movieCrewRepo.findAllByPersonId(id);
            movieCastRepo.deleteAll(movieCasts);
            movieCrewRepo.deleteAll(movieCrews);
            personRepo.delete(actor.get());
        }
    }

    public Page<Person> findAllActors(Integer size, Integer index) {

        Page<Person> actors =personRepo.findAllActors(Pageable.ofSize(size).withPage(index/size));

        return actors;
    }

    public List<Person> findAllActors() {
        return personRepo.findAllActors();
    }

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Person findById(Long personId) {
        return personRepo.getById(personId);
    }

    public Person findActorById(Long actorId) {
        return personRepo.findActorBypersonId(actorId);
    }

     Long generateRandomId(){
        Random rand = new Random();
        int range = 1999999999;
        Long id = (long) (rand.nextInt(range) + 1);

        while (personRepo.findById(id).isPresent()) {
            id = (long) (rand.nextInt(range) + 1);
        }
        return id;
    }
}
