package es.uji.agdc.videoclub.validators;

import es.uji.agdc.videoclub.models.AbstractEntity;
import es.uji.agdc.videoclub.services.utils.Result;

/**
 * Validates an entity
 */
public interface Validator<T extends AbstractEntity> {

    /**
     * Checks an {@link AbstractEntity} against the business rules
     * @param entity The {@link AbstractEntity} to be checked
     * @return An OK result if the entity is valid
     */
    Result validate(T entity);
}
