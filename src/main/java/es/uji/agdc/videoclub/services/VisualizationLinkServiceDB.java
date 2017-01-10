package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.VisualizationLink;
import es.uji.agdc.videoclub.repositories.VisualizationLinkRepository;
import es.uji.agdc.videoclub.services.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return null;
    }

    @Override
    public Optional<VisualizationLink> findBy(VisualizationLinkQueryTypeSimple field, String value) {
        return null;
    }

    @Override
    public Stream<VisualizationLink> findAllBy(VisualizationLinkQueryTypeSimple field, String value) {
        return null;
    }

    @Override
    public Result remove(String token) {
        return null;
    }
}
