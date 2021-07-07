package com.diazmic.VinylDatabaseAPI.service;

import com.diazmic.VinylDatabaseAPI.db.dao.impl.VinylDao;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

/*
    TODO: - CREATE A EXCEPTION CLASS FOR MODEL "Vinyl"
            + Booleans lead to more logic in the controller
*/
@Service
public class VinylService {

    @Autowired
    private VinylDao vinylDao;

    /**
     * Saves input vinyl to database.
     * @param targetVinyl The target vinyl one wants to save
     * @return The target {@code Vinyl} that was inputted.
     * @throws Exception when target Vinyl is either null, the Vinyl's catalog number is blank, or if the number is an
     * empty string
     */
    public Vinyl saveVinyl(Vinyl targetVinyl) throws Exception {
        if(targetVinyl == null || targetVinyl.getCatalogNumber().isBlank() || targetVinyl.getCatalogNumber().isEmpty())
            throw new Exception("Vinyl is null or Catalog Number is blank");
        return vinylDao.create(targetVinyl);
    }

    /**
     * Return all Vinyls from database
     * @return {@code Collection<Vinyl>} of the whole vinyl database.
     */
    public Collection<Vinyl> getAllVinyls(){
        return vinylDao.readAll();
    }

    /**
     * Get vinyl from database from catalog number.
     * @param catalogNumber Target catalog number for vinyl
     * @return the target {@code Vinyl}
     * @throws Exception if the vinyl doesnt exist in database.
     */
    public Vinyl getVinyl(String catalogNumber) throws Exception {
        for(Vinyl vinyl : vinylDao.readAll()){
            if(vinyl.getCatalogNumber().equals(catalogNumber))
                return vinyl;
        }
        throw new Exception("Vinyl does not exist in Database");
    }

    /**
     * Gets all vinyls with associated query name  .
     * @param vinylName the vinyls name for query in Database
     * @return {@code Collection<Vinyls>} of that query.
     */
    public Collection<Vinyl> vinylNameQuery(String vinylName) throws Exception {
        if(vinylName == null)
            throw new Exception("Search input is null.");
        if(vinylName.isBlank())
            return Collections.emptyList();
        return vinylDao.vinylNameQueue(vinylName);
    }

    /**
     * Edits Vinyl in database.
     * @param catalogNumber The id for the target {@code Vinyl}
     * @param effectedVinyl The {@code Vinyl} with all changes
     * @return the new edited {@code Vinyl}
     */
    public Vinyl editVinyl(String catalogNumber, Vinyl effectedVinyl) throws Exception {
        if(vinylDao.read(catalogNumber).isEmpty())
            throw new Exception("Vinyl doesn't exists. Can not edit non-existent Vinyl.");
        if(!vinylDao.update(effectedVinyl,catalogNumber))
            throw new Exception("Vinyl can not be edited.");
        return vinylDao.read(catalogNumber).get();
    }

    /**
     * Delete Vinyl from database by catalog number.
     * @param catalogNumber Target Catalog Number for target vinyl to delete
     * @return deleted Vinyl
     */
    public Vinyl deleteVinyl(String catalogNumber) throws Exception {
        if(vinylDao.read(catalogNumber).isPresent()) {
            Vinyl targetVinyl = vinylDao.read(catalogNumber).get();
            vinylDao.delete(catalogNumber);
            return targetVinyl;
        }
        else{
            throw new Exception("Can not remove non existent vinyl");
        }
    }
}
