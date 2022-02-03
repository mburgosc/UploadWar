package com.liceu.practicamovies.services;

import com.liceu.practicamovies.models.*;
import com.liceu.practicamovies.models.MovieCast.MovieCastId;
import com.liceu.practicamovies.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    @Autowired
    MovieCastRepo movieCastRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    GenreRepo genreRepo;

    @Autowired
    GenderRepo genderRepo;

    @Autowired
    KeywordRepo keywordRepo;

    public List<Movie> findAll(int size,int index) {

        Page<Movie> movies =  movieRepo.findAll(Pageable.ofSize(size).withPage(index/size));

        return  movies.getContent();
    }

    public Page<Movie> findAllByTitle(String title,int size,int index) {
        Page<Movie> movies = movieRepo.findAllByTitleContaining(Pageable.ofSize(size).withPage(index/size), title);

        return movies;
    }

    public List<Movie> findAllByTitle(String title) {
        List<Movie> movies = movieRepo.findAllByTitleContaining(title);

        return movies;
    }

    public List<Movie> findAll() {
        return movieRepo.findAll();
    }

    public List<Genre> findAllGenres(String term) {
        return genreRepo.findAllByGenreNameContaining(term);
    }

    public List<String> findAllCharacters(String term) {
        List<MovieCast> movieCasts = movieCastRepo.findAllByCharacterNameContaining(term);
        List<String> charactersName = movieCasts
                .stream()
                .map( movieCast -> movieCast.getCharacterName())
                .collect(Collectors.toList());
        return charactersName;
    }

    public Page<Movie> findAllByActorName(String actorName, Integer size, Integer index) {
        Page<Movie> movies = movieRepo.findAllByMovieCast_PersonPersonName(Pageable.ofSize(size).withPage(index/size),actorName);

        return movies;
    }



    public Page<Movie> findAllByCharacter(String characterName, Integer size, Integer index) {

        Page<Movie> movies = movieRepo.findAllByMovieCastCharacterName(Pageable.ofSize(size).withPage(index/size),characterName);

        return movies;
    }

    public Page<Movie> findAllByGenre(String genreName, Integer size, Integer index) {

        Page<Movie> movies =  movieRepo.findAllByGenres(Pageable.ofSize(size).withPage(index/size),genreName);

        return movies;
    }

    public Page<Movie> findAllByDirector(String directorName, Integer size, Integer index) {

        Page<Movie> movies= movieRepo.findAllByDirector(Pageable.ofSize(size).withPage(index/size),directorName);

        return movies;
    }

    public Movie save(
                        String title, Long budget, String homepage, String overview, double popularity,
                        Date relaseDate, BigInteger revenue, int runtime, String movieStatus, String tagline,
                        double voteAverage, int voteCount
                    ) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setBudget(budget);
        movie.setHomepage(homepage);
        movie.setOverview(overview);
        movie.setPopularity(popularity);
        movie.setReleaseDate(relaseDate);
        movie.setRevenue(revenue);
        movie.setRuntime(runtime);
        movie.setMovieStatus(movieStatus);
        movie.setTagline(tagline);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);

        Long maxId = generateRandomId();
        movie.setMovieId(maxId+1);

        movieRepo.save(movie);
        return movie;
    }

    public void addMovieCast(Long actorId, String titleMovie, String characterName,int genderId) {
        Person actor = personRepo.findActorBypersonId(actorId);
        Movie movie = movieRepo.findAllByTitle(titleMovie).get(0);

        MovieCast movieCast = new MovieCast();

        Long maxCastOrder = movieCastRepo.getMaxCastOrder();
        movieCast.setCastOrder(Math.toIntExact(++maxCastOrder));

        movieCast.setMovieId(movie.getMovieId());
        movieCast.setMovie(movie);

        movieCast.setPersonId(actorId);
        movieCast.setPerson(actor);

        movieCast.setCharacterName(characterName);

        Gender gender = genderRepo.getById(genderId);

        movieCast.setGenderId(genderId);
        movieCast.setGender(gender);

        if (!movie.getTitle().equals("none")){
            MovieCastId movieCastId = new MovieCastId(
                                                        movieRepo.findByTitle("none").getMovieId(),
                                                        0,
                                                        actorId
                                                     );
            /*
             * si se va a añadir a un actor que esta enlazado con la pelicula generica "none"
             * ya no es necesario que este enlazado con esta pelicula por lo cual el enlace
             * se borra (El sentido de crear la pelicula "none" se explica en la linea 60 de personService)
             */
            movieCastRepo.delete(movieCastRepo.getById(movieCastId));
        }

        movieCastRepo.save(movieCast);
    }

    public Optional<Movie> findById(Long movieId) {
        return movieRepo.findById(movieId);
    }

    public Genre findGenreByMovieId(Long movieId) {
        return genreRepo.findByMovieId(movieId);
    }

    public void update(Long movieId, String title, Long budget, String homepage, String overview, double popularity,
                       Date relaseDate, BigInteger revenue, int runtime, String movieStatus, String tagline,
                       double voteAverage, int voteCount) {
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setTitle(title);
        movie.setBudget(budget);
        movie.setHomepage(homepage);
        movie.setOverview(overview);
        movie.setPopularity(popularity);
        movie.setReleaseDate(relaseDate);
        movie.setRevenue(revenue);
        movie.setRuntime(runtime);
        movie.setMovieStatus(movieStatus);
        movie.setTagline(tagline);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);
        movieRepo.save(movie);

    }

    public List<Keyword> findMovieKeywords(Long movieId) {
        return  keywordRepo.findAllByMovieId(movieId);
    }

    public void addKeywordToMovie(Long movieId, String keywordName) {
        Movie movie = movieRepo.findById(movieId).get();
        Optional<Keyword> dbKeyword = keywordRepo.findByKeywordName(keywordName);
        if (!dbKeyword.isPresent()){
            Keyword keyword = new Keyword();
            Long maxId = keywordRepo.getMaxId();
            keyword.setKeywordId(maxId +1);
            keyword.setKeywordName(keywordName);
            keywordRepo.save(keyword);
            movie.getKeywords().add(keyword);
        }else {
            movie.getKeywords().add(dbKeyword.get());
        }


        movieRepo.save(movie);
    }

    public void addExistentKeywordToMovie(String movieTitle, Long keywordId) {
        Movie movie = movieRepo.findByTitle(movieTitle);
        Keyword keyword = keywordRepo.getById(keywordId);
        movie.getKeywords().add(keyword);
        movieRepo.save(movie);
    }

    public void removeMovieKeywords(Long movieId, List<String> keywordNames) {
        Movie movie = movieRepo.getById(movieId);
        List<String> movieKeywordNames = movie.getKeywords()
                .stream()
                .map( keyword -> keyword.getKeywordName())
                .collect(Collectors.toList());


        /*
        * se filtran los nombres de keyword que el usuario quiere eliminar
        * para asegurarse de que todos estan asociados a la pelicula
        * se busca todos los keywords a traves de sus nombres
        * */
        List<Keyword> parameterKeywords = keywordNames
                .stream()
                .filter( name -> movieKeywordNames.contains(name))
                .map( name -> keywordRepo.findByKeywordName(name).get())
                .collect(Collectors.toList());

        movie.getKeywords().removeAll(parameterKeywords);
        movieRepo.save(movie);
    }

    public Page<Keyword> findAllKeywords(Integer size, Integer index) {
        return keywordRepo.findAll(Pageable.ofSize(size).withPage(index / size));
    }

    public Page<Keyword> findAllKeywordsByName(String keywordName, Integer size, Integer index) {
        return keywordRepo.findByKeywordNameContaining(Pageable.ofSize(size).withPage(index / size),keywordName);
    }

    public List<Keyword> findAllKeywords(String term) {
        return keywordRepo.findByKeywordNameContaining(term);
    }

    public Keyword findKeywordById(Long keywordId) {
        return keywordRepo.getById(keywordId);
    }

    public void deleteKeyword(Long keywordId) {
        Keyword keyword = keywordRepo.getById(keywordId);
        //se eliminan todas las relacciones con la tabla movie_keyword
        keyword.getMovies().clear();
        keywordRepo.save(keyword);
        keywordRepo.delete(keyword);
    }

    public void removeMovieKeyword(String titleMovie, Long keywordId) {
        Movie movie = movieRepo.findByTitle(titleMovie);
        Keyword keyword = keywordRepo.getById(keywordId);

        movie.getKeywords().remove(keyword);


    }
    Long generateRandomId(){
        Random rand = new Random();
        int range = 1999999999;
        Long id = (long) (rand.nextInt(range) + 1);

        while (movieRepo.findById(id).isPresent()) {
            id = (long) (rand.nextInt(range) + 1);
        }
        return id;
    }
}
