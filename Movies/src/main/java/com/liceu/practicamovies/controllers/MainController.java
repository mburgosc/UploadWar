package com.liceu.practicamovies.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liceu.practicamovies.models.*;
import com.liceu.practicamovies.services.MovieService;
import com.liceu.practicamovies.services.PersonService;
import org.apache.tomcat.util.json.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    MovieService movieService;

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String home(){
        return "homepage";
    }

    // TABLES
    @GetMapping("/movies")
    public String getMovies(){
        return "movies";
    }
    @GetMapping("/actors")
    public String getActors(){
        return "actors";
    }
    @GetMapping("/keywords")
    public String getKeywords(){
        return "keywords";
    }

    // MODEL'S VIEWS
    @GetMapping("/keyword/{keywordId}")
    public String getKeyword(Model model,@PathVariable Long keywordId){
        Keyword keyword = movieService.findKeywordById(keywordId);

        model.addAttribute("keyword",keyword);
        return "keywordpage";
    }

    @PostMapping("/keyword/{keywordId}")
    public String postKeyword(@PathVariable Long keywordId, @RequestParam String titleMovie) {
        movieService.addExistentKeywordToMovie(titleMovie,keywordId);
        return "redirect:/keyword/"+keywordId;
    }

    @GetMapping("/actor/{actorId}")
    public String getActor(Model model,@PathVariable Long actorId){
        Person actor = personService.findActorById(actorId);
        List<Movie> actorMovies = actor.getMovieCast()
                .stream()
                .map((movieCast -> movieCast.getMovie()))
                .collect(Collectors.toList());

        model.addAttribute("actor",actor);
        model.addAttribute("actorMovies",actorMovies);
        return "actorpage";
    }

    @PostMapping("/actor/{actorId}")
    public String postActor(@PathVariable Long actorId,
                            @RequestParam String titleMovie,
                            @RequestParam String characterName,
                            @RequestParam int genderId
    ) {
        movieService.addMovieCast(actorId,titleMovie,characterName,genderId);

        return "redirect:/actor/"+actorId;
    }

    @GetMapping("/movie/{movieId}")
    public String getMovie(Model model,@PathVariable Long movieId) throws ParseException {
        Optional<Movie> movie = movieService.findById(movieId);
        if (!movie.isPresent()){
            // TROW EXCEPTION
        }
        model.addAttribute("movie",movie.get());
        return "moviepage";
    }

    @PostMapping("/movie/{movieId}")
    public String postMovie(Model model,
                            @PathVariable(required = false) Long movieId,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) Long budget,
                            @RequestParam(required = false) String homepage,
                            @RequestParam(required = false) String overview,
                            @RequestParam(required = false) double popularity,
                            @RequestParam(required = false) String releaseDateString,
                            @RequestParam(required = false) BigInteger revenue,
                            @RequestParam(required = false) int runtime,
                            @RequestParam(required = false) String movieStatus,
                            @RequestParam(required = false) String tagline,
                            @RequestParam(required = false) double voteAverage,
                            @RequestParam(required = false) int voteCount
                            ) throws ParseException {
        Date relaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateString);

        movieService.update(
                movieId,title,budget,homepage,overview,popularity,relaseDate,
                revenue,runtime,movieStatus,tagline,voteAverage,voteCount
        );

        return "redirect:/movie/"+movieId;
    }

    // CREATE FORMS
    @GetMapping("/createactor")
    public String getActorForm(Model model){
        return "createactor";
    }

    @PostMapping("/createactor")
    public String createActor(Model model,@RequestParam String actorName){

        Person actor = personService.save(actorName);

        return "redirect:/actor/"+actor.getPersonId();
    }

    @GetMapping("/createmovie")
    public String createMovie(Model model){
        return "createMovie";
    }

    @PostMapping("/createmovie")
    public String createMovie(Model model,
                              @RequestParam String title,
                              @RequestParam Long budget,
                              @RequestParam String homepage,
                              @RequestParam String overview,
                              @RequestParam double popularity,
                              @RequestParam String releaseDateString,
                              @RequestParam BigInteger revenue,
                              @RequestParam int runtime,
                              @RequestParam String movieStatus,
                              @RequestParam String tagline,
                              @RequestParam double voteAverage,
                              @RequestParam int voteCount
    ) throws ParseException {

        Date relaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateString);
        Movie movie = movieService.save(
                title,budget,homepage,overview,popularity,relaseDate,
                revenue,runtime,movieStatus,tagline,voteAverage,voteCount
        );
        return "redirect:/movie/"+movie.getMovieId();
    }

    @PostMapping("/addkeyword")
    public String addKeyword(@RequestParam String keywordName, @RequestParam Long movieId){
        movieService.addKeywordToMovie(movieId,keywordName);
        return "redirect:/movie/"+movieId;
    }

    // DELETE REQUESTS
    @PostMapping("/deleteactor")
    public String deleteActor(Model model,@RequestParam Long actorId){
        personService.deleteActorCastsAndCrew(actorId);
        return "redirect:/actors";

    }

    // DELETE REQUESTS
    @PostMapping("/deletekeyword")
    public String deleteKeyword(Model model,@RequestParam Long keywordId){
        movieService.deleteKeyword(keywordId);
        Keyword keyword = movieService.findKeywordById(keywordId);

        return "redirect:/keywords";

    }

    @PostMapping("/removemoviekeywords")             // JSON ARRAY
    public String removeMovieKeyword(Model model,@RequestParam String JsonKeywords,
                                     @RequestParam Long movieId) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        List<String> keywordNames = Arrays.asList(mapper.readValue(JsonKeywords, String[].class));
        movieService.removeMovieKeywords(movieId,keywordNames);
        return "redirect:/movie/"+movieId;
    }
    // SEARCH RESPONSES

    @RequestMapping(value = "/getkeywords", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getKeywords(@RequestParam(required = false) Integer length,
                                                      @RequestParam(required = false) Integer start){


        Page<Keyword> keywords = movieService.findAllKeywords(length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",keywords.getContent());

        map.put("recordsTotal" ,keywords.getTotalElements());
        map.put("recordsFiltered",keywords.getTotalElements());
        return map;
    }
    @RequestMapping(value = "/getkeywordsbyname", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getKeywordsByName(
            @RequestParam String search,
            @RequestParam(required = false) Integer length,
            @RequestParam(required = false) Integer start){


        Page<Keyword> keywords = movieService.findAllKeywordsByName(search,length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",keywords.getContent());

        map.put("recordsTotal" ,keywords.getTotalElements());
        map.put("recordsFiltered",keywords.getTotalElements());
        return map;
    }

    @RequestMapping(value = "/getactors", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getActors(@RequestParam(required = false) Integer length,
                                                      @RequestParam(required = false) Integer start){


        Page<Person> actors = personService.findAllActors(length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",actors.getContent());

        map.put("recordsTotal" ,actors.getTotalElements());
        map.put("recordsFiltered",actors.getTotalElements());
        return map;
    }
    @RequestMapping(value = "/getactorsbyname", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getActorsByName(
                                                      @RequestParam String search,
                                                      @RequestParam(required = false) Integer length,
                                                      @RequestParam(required = false) Integer start){


        Page<Person> actors = personService.findAllActorsByName(search,length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",actors.getContent());

        map.put("recordsTotal" ,actors.getTotalElements());
        map.put("recordsFiltered",actors.getTotalElements());
        return map;
    }

    @RequestMapping(value = "/getmovies", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getMovies(@RequestParam(required = false) Integer length,
                                                      @RequestParam(required = false) Integer start){


        List<Movie> movies = movieService.findAll(length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",movies);

        map.put("recordsTotal" ,movieService.findAll().size());
        map.put("recordsFiltered",movieService.findAll().size());
        return map;
    }

    @RequestMapping(value = "/getmoviesbyactor", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getMoviesByActor(
                                                        @RequestParam String search,
                                                        @RequestParam(required = false) Integer length,
                                                        @RequestParam(required = false) Integer start){

        Page<Movie> movies = movieService.findAllByActorName(search,length,start);
        Map<String,Object> map = new HashMap<>();

        map.put("data",movies.getContent());
        map.put("recordsTotal" ,movies.getTotalElements());
        map.put("recordsFiltered" ,movies.getTotalElements());
        return  map;
    }

    @RequestMapping(value = "/getmoviesbytitle", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getMoviesByTitle(
                                                        @RequestParam String search,
                                                        @RequestParam(required = false) Integer length,
                                                        @RequestParam(required = false) Integer start){

        Page<Movie> movies = movieService.findAllByTitle(search,length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",movies.getContent());
        map.put("recordsTotal" ,movieService.findAll().size());
        map.put("recordsFiltered" ,movies.getTotalElements());
        return  map;
    }

    @RequestMapping(value = "/getmoviesbycharacter", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getMoviesByCharacter(
                                                        @RequestParam String search,
                                                        @RequestParam(required = false) Integer length,
                                                        @RequestParam(required = false) Integer start){

        Page<Movie> movies = movieService.findAllByCharacter(search,length,start);
        Map<String,Object> map = new HashMap<>();

        map.put("data",movies.getContent());
        map.put("recordsTotal" ,movieService.findAll().size());
        map.put("recordsFiltered" ,movies.getTotalElements());
        return  map;
    }

    @RequestMapping(value = "/getmoviesbygenre", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getMoviesByGenre(
                                                        @RequestParam String search,
                                                        @RequestParam(required = false) Integer length,
                                                        @RequestParam(required = false) Integer start){

        Page<Movie> movies = movieService.findAllByGenre(search,length,start);
        Map<String,Object> map = new HashMap<>();

        map.put("data",movies.getContent());
        map.put("recordsTotal" ,movieService.findAll().size());
        map.put("recordsFiltered" ,movies.getTotalElements());
        return  map;
    }

    @RequestMapping(value = "/getmoviesbydirector", produces = {"application/json"})
    public @ResponseBody Map<String,Object> getMoviesByDirector(
                                                        @RequestParam String search,
                                                        @RequestParam(required = false) Integer length,
                                                        @RequestParam(required = false) Integer start){

        Page<Movie> movies = movieService.findAllByDirector(search,length,start);
        Map<String,Object> map = new HashMap<>();
        map.put("data",movies.getContent());
        map.put("recordsTotal" ,movieService.findAll().size());
        map.put("recordsFiltered" ,movies.getTotalElements());
        return  map;
    }

    //AUTO COMPLETE RESPONSES
    @RequestMapping(value = "/getmoviestitle", produces = {"application/json"})
    public @ResponseBody List<String> getmoviesTitle(@RequestParam String term){
        List<Movie> movies = movieService.findAllByTitle(term);
        List<String> titles = movies
                .stream()
                .map(movie -> movie.getTitle())
                .collect(Collectors.toList());
        return titles;
    }

    @RequestMapping(value = "/getactorsname", produces = {"application/json"})
    public @ResponseBody List<String> getActorsName(@RequestParam String term){
        List<Person> actors = personService.findAllActorsByName(term);
        List<String> names = actors
                .stream()
                .map(actor -> actor.getPersonName())
                .collect(Collectors.toList());
        return names;
    }

    @RequestMapping(value = "/getdirectorsname", produces = {"application/json"})
    public @ResponseBody List<String> getDirectorsName(@RequestParam String term){
        List<Person> directors = personService.findAllDirectorsByName(term);
        List<String> names = directors
                .stream()
                .map(actor -> actor.getPersonName())
                .collect(Collectors.toList());
        return names;
    }

    @RequestMapping(value = "/getgenres", produces = {"application/json"})
    public @ResponseBody List<String> getGenres(@RequestParam String term){
        List<Genre> genres = movieService.findAllGenres(term);
        List<String> names = genres
                .stream()
                .map(genre -> genre.getGenreName())
                .collect(Collectors.toList());
        return names;
    }

    @RequestMapping(value = "/getcharacters", produces = {"application/json"})
    public @ResponseBody List<String> getCharacters(@RequestParam String term){
        List<String> characters = movieService.findAllCharacters(term);

        return characters;
    }

    @RequestMapping(value = "/getkeywordsname", produces = {"application/json"})
    public @ResponseBody List<String> getKeywordsName(@RequestParam String term){
        List<String> keywords = movieService.findAllKeywords(term)
                .stream()
                .map(keyword -> keyword.getKeywordName())
                .collect(Collectors.toList());

        return keywords;
    }

}
