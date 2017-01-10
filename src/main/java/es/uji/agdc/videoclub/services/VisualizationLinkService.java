package es.uji.agdc.videoclub.services;

import es.uji.agdc.videoclub.models.VisualizationLink;
import es.uji.agdc.videoclub.services.utils.Result;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Visualization link service interface that acts as a gateway between the user and the business logic.
 *
 * Every visualization link service must implement this interface.
 */
public interface VisualizationLinkService {

    /**
     * Creates a visualization link if it meets business logic constraints.
     * If not returns an ERROR {@link Result} telling what went wrong.
     * @param visualizationLink The {@link VisualizationLink} to be created.
     * @return An OK {@link Result}, if everything went fine.
     * If not an ERROR with a message and the fields that do not meet the requisites.
     */
    Result create(VisualizationLink visualizationLink);

    /**
     * Tries to find a visualization link by the specified field
     * @param field The field that has to be looked at
     * @param value The value of the field that has to be matched
     * @return A filled {@link Optional} with the {@link VisualizationLink} that was found
     * or an empty one if no {@link VisualizationLink} was found
     */
    Optional<VisualizationLink> findBy(VisualizationLinkQueryTypeSimple field, String value);

    /**
     * Tries to find a list of visualization links by the specified field
     * @param field The field that has to be looked at
     * @param value The value of the field that has to be matched
     * @return A filled {@link Stream} with the {@link VisualizationLink} that were found
     * or an empty one if no {@link VisualizationLink} was found
     */
    Stream<VisualizationLink> findAllBy(VisualizationLinkQueryTypeMultiple field, String value);

    /**
     * Removes an existing visualization link
     * @param token The token of the {@link VisualizationLink} to be deleted
     * @return An OK {@link Result}, if everything went fine.
     * If not an ERROR with a message and the fields that do not meet the requisites.
     */
    Result remove(String token);
}
