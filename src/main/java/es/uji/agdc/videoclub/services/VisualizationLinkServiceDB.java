package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.VisualizationLink;
import es.uji.agdc.videoclub.repositories.VisualizationLinkRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import es.uji.agdc.videoclub.services.utils.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by alberto on 10/1/17.
 */
@Service
public class VisualizationLinkServiceDB implements VisualizationLinkService {

    private UserService userService;
    private MovieService movieService;
    private VisualizationLinkRepository repository;

    @Autowired
    public VisualizationLinkServiceDB(UserService userService,
                                      MovieService movieService, VisualizationLinkRepository repository) {
        this.userService = userService;
        this.movieService = movieService;
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result create(VisualizationLink visualizationLink) {

        User user = visualizationLink.getUser();
        Result userNotFound = ResultBuilder.error("USER_NOT_FOUND");
        if (user.isNew()) return userNotFound;

        Optional<User> possibleUser = userService.findBy(UserQueryTypeSingle.ID, user.getId().toString());
        if (!possibleUser.isPresent()) return userNotFound;

        Movie movie = visualizationLink.getMovie();
        Result movieNotFound = ResultBuilder.error("MOVIE_NOT_FOUND");
        if (movie.isNew()) return movieNotFound;

        Optional<Movie> possibleMovie = movieService.findBy(MovieQueryTypeSingle.ID, movie.getId().toString());
        if (!possibleMovie.isPresent()) return movieNotFound;

        LocalDateTime expeditionDate = visualizationLink.getExpeditionDate();
        if (expeditionDate == null || expeditionDate.compareTo(LocalDateTime.now()) > 0)
            return ResultBuilder.error("INVALID_EXPEDITION_DATE");

        long linksCount = repository.findByMovie_Id(movie.getId()).count();

        Optional<VisualizationLink> possibleLink = repository.findByUserAndMovie(user, movie);
        if (possibleLink.isPresent())
            return ResultBuilder.error("ALREADY_WATCHING");

        if (linksCount >= movie.getAvailableCopies())
            return ResultBuilder.error("NO_COPIES_AVAILABLE");

        repository.save(visualizationLink);

        return ResultBuilder.ok();
    }

    @Override
    public Optional<VisualizationLink> findBy(VisualizationLinkQueryTypeSimple field, String value) {
        throw new Error("Unimplemented");
    }

    @Override
    public Stream<VisualizationLink> findAllBy(VisualizationLinkQueryTypeMultiple field, String value) {
        if (isEmpty(value)) return Stream.empty();

        switch (field) {
            case MOVIE:
                return findIfValidMovieId(value);
            case USER:
                return findIfValidUserId(value);
            default:
                throw new Error("Unimplemented");
        }
    }

    private Stream<VisualizationLink> findIfValidMovieId(String id) {
        if (!isLong(id)) return Stream.empty();
        return repository.findByMovie_Id(Long.parseLong(id));
    }

    private Stream<VisualizationLink> findIfValidUserId(String id) {
        if (!isLong(id)) return Stream.empty();
        return repository.findByUser_Id(Long.parseLong(id));
    }

    private boolean isLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    @Override
    public Result remove(String token) {
        throw new Error("Unimplemented");
    }
}
