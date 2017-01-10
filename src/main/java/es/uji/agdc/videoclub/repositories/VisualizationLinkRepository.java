package es.uji.agdc.videoclub.repositories;

import es.uji.agdc.videoclub.models.VisualizationLink;
import java.util.stream.Stream;

import java.util.Optional;

/**
 * Repository (DAO) that permits CRUD operations on user entities
 */
public interface VisualizationLinkRepository extends CrudRepositoryJ8<VisualizationLink, Long> {

    /**
     * Tries to find a visualization link via its token
     * @param string the token of the visualization link
     * @return A filled {@link Optional} with the {@link VisualizationLink} that was found
     * or an empty one if no {@link VisualizationLink} was found
     */
    Optional<VisualizationLink> findByToken(String string);

    /**
     * Tries to find visualization links via their movie
     * @param movieId the id of the movie of the visualization links
     * @return A filled {@link Stream} that contains the visualization links that match the statement
     * or an empty one if no {@link VisualizationLink} was found
     */
    Stream<VisualizationLink> findByMovie_Id(long movieId);

    /**
     * Tries to find visualization links via their user
     * @param userId the id of the user of the visualization links
     * @return A filled {@link Stream} that contains the visualization links that match the statement
     * or an empty one if no {@link VisualizationLink} was found
     */
    Stream<VisualizationLink> findByUser_Id(long userId);
}
