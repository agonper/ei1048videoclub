package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Actor;
import es.uji.agdc.videoclub.models.Director;
import es.uji.agdc.videoclub.models.Genre;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import es.uji.agdc.videoclub.services.utils.StreamMerger;
import es.uji.agdc.videoclub.validators.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alberto on 11/12/2016.
 */
@Service
public class MovieServiceDB implements MovieService{

    private static Logger log = LoggerFactory.getLogger(MovieServiceDB.class);

    private MovieRepository movieRepository;
    private MovieAssetService assetService;
    private Validator<Movie> validator;

    @Autowired
    public MovieServiceDB(MovieRepository movieRepository, MovieAssetService assetService, Validator<Movie> validator) {
        this.movieRepository = movieRepository;
        this.assetService = assetService;
        this.validator = validator;
    }

    @Override
    public Result create(Movie movie) {

        Result ALREADY_EXISTS = ResultBuilder.error("MOVIE_ALREADY_EXISTS");

        if (!movie.isNew()) {
            log.warn("Tried to create an already persisted movie with ID: " + movie.getId());
            return ALREADY_EXISTS;
        }

        Result validatorResult = validator.validate(movie);
        if (validatorResult.isError()) {
            return validatorResult;
        }

        if (movieRepository.findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear()).isPresent()) {
            return ALREADY_EXISTS;
        }

        cleanupAssets(movie);

        Movie savedMovie = movieRepository.save(movie);
        log.info("Movie saved with ID: " + savedMovie.getId());
        return ResultBuilder.ok();
    }

    private void cleanupAssets(Movie movie) {
        // TODO find a way to cleanup this

        // Grab those actors that already exist on the database and replace them
        movie.setActors(movie.getActors().stream().map(actor -> {
            Optional<Actor> possibleActor = assetService.findActorByName(actor.getName());
            return possibleActor.isPresent() ? possibleActor.get() : actor;
        }).collect(Collectors.toList()));

        // Grab those directors that already exist on the database and replace them
        movie.setDirectors(movie.getDirectors().stream().map(director -> {
            Optional<Director> possibleDirector = assetService.findDirectorByName(director.getName());
            return possibleDirector.isPresent() ? possibleDirector.get() : director;
        }).collect(Collectors.toList()));

        // Grab those genres that already exist on the database and replace them
        movie.setGenres(movie.getGenres().stream().map(genre -> {
            Optional<Genre> possibleGenre = assetService.findGenreByName(genre.getName());
            return possibleGenre.isPresent() ? possibleGenre.get() : genre;
        }).collect(Collectors.toList()));
    }

    @Override
    public Optional<Movie> findBy(MovieQueryTypeSingle query, String... values) {
        if (values.length == 0) {
            log.warn("findBy() called with 0 values");
            return Optional.empty();
        }

        switch (query) {
            case ID:
                if (values.length != 1) {
                    log.warn("findBy(ID) called with " + values.length + " values");
                    return Optional.empty();
                }
                return findOneIfValidId(values[0]);
            case TITLE_AND_YEAR:
                if (values.length != 2) {
                    log.warn("findBy(TITLE_AND_YEAR) called with " + values.length + " values");
                    return Optional.empty();
                }
                return findOneIfValidYear(values[0], values[1]);
            default:
                throw new Error("Unimplemented");
        }
    }

    private Optional<Movie> findOneIfValidId(String value) {
        try {
            return movieRepository.findOne(Long.valueOf(value));
        } catch (NumberFormatException e) {
            log.warn("ID couldn't be parsed. " + e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Movie> findOneIfValidYear(String title, String year) {
        try {
            return movieRepository.findByTitleIgnoreCaseAndYear(title, Integer.valueOf(year));
        } catch (NumberFormatException e) {
            log.warn("Year couldn't be parsed. " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Stream<Movie> findAllBy(MovieQueryTypeMultiple query, String[] values) {

        if (values.length == 0) {
            log.warn(String.format("findAllBy(%s): Called without values", query.toString()));
            return Stream.empty();
        }

        Arrays.stream(values).forEach((value) -> {
            if (value.split(" ").length > 1) throw new Error("Each value has to be a word. No spaces are allowed");
        });

        switch (query) {
            case TITLE:
                StreamMerger<Movie> movieStreamMerger = new StreamMerger<>();
                Arrays.stream(values)
                        .map((value) -> movieRepository.findByTitleContainsIgnoreCase(value))
                        .forEach(movieStreamMerger::addStream);
                return movieStreamMerger.merge();
        }

        return Stream.empty();
    }

    @Override
    public Stream<Movie> findAll() { //FIXME Remove me
        return movieRepository.findAll();
    }

    @Override
    public Result update(Movie movie) {
        throw new Error("Unimplemented");
    }

    @Override
    public Result remove(String title, int year) {
        throw new Error("Unimplemented");
    }
}
