package es.uji.agdc.videoclub.repositories;

import es.uji.agdc.videoclub.models.VisualizationLink;

import java.util.Optional;

/**
 * Repository (DAO) that permits CRUD operations on user entities
 */
public interface VisualizationLinkRepository extends CrudRepositoryJ8<VisualizationLink, Long> {

    /**
     * Tries to find a visualization via its token
     * @param string the token of the visualization link
     * @return A filled {@link Optional} with the {@link VisualizationLink} that was found
     * or an empty one if no {@link VisualizationLink} was found
     */
    Optional<VisualizationLink> findByToken(String string);
}
