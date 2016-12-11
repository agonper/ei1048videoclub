package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.repositories.MovieRepository;
import es.uji.agdc.videoclub.services.utils.Result;
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

    @Autowired
    public MovieServiceDB(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Result create(Movie movie) {
        return null;
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
