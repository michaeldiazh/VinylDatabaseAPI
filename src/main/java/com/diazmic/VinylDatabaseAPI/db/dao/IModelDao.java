package com.diazmic.VinylDatabaseAPI.db.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;

public interface IModelDao<Model,Id>{


    /**
     * Creates a new model to be appened to the MongoDB Database
     * @param newModel the target model
     * @return the new {@code Model}
     */
    Model create(Model newModel) throws InvocationTargetException, IllegalAccessException;

    /**
     * Reads an id and return back a Model
     * @param targetId the Id of the model
     * @return {@code Optional<Model>}
     */
    Optional<Model> read(Id targetId);

    /**
     * Updates an Entity in the Database
     * @param newModel the new model that is affected
     * @param targetId the model that needs to be updated
     * @return {@code true} if Entity could be updated, {@code false} otherwise
     */
    boolean update(Model newModel, Id targetId) throws InvocationTargetException, IllegalAccessException;

    /**
     * Deletes an Entity in the Database
     * @param targetID the Id of the target Model
     * @return {@code true} if Entity is successfully deleted.
     */
    boolean delete(Id targetID);

    /**
     * Returns a collection of all the model object in the database.
     * @return {@code Collection<Model>} from the database.
     */
    Collection<Model> readAll();
}
