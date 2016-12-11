package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alberto on 11/12/2016.
 */
@Service
public class MovieServiceDB implements MovieService{

    private MovieRepository movieRepository;
    private MovieAssetService assetService;

    @Autowired
    public MovieServiceDB(MovieRepository movieRepository, MovieAssetService assetService) {
        this.movieRepository = movieRepository;
        this.assetService = assetService;
    }

    @Override
    public Result create(Movie movie) {

        Result ALREADY_EXISTS = ResultBuilder.error("MOVIE_ALREADY_EXISTS");

        if (!movie.isNew()) {
            return ALREADY_EXISTS;
        }

        if (movieRepository.findByTitleIgnoreCaseAndYear(movie.getTitle(), movie.getYear()).isPresent()) {
            return ALREADY_EXISTS;
        }

        movieRepository.save(movie);
        return ResultBuilder.ok();
    }

    @Override
    public Optional<Movie> findBy(MovieQueryTypeSingle query, String... values) {
        return null;
    }

    @Override
    public Stream<Movie> findAllBy(MovieQueryTypeMultiple query, String... values) {
        throw new Error("Unimplemented");
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
