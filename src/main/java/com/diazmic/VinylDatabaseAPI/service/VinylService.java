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
            + Create Mock Dao invocations:
                # Create Method (setUpDaoCreate())
                # Read Method (setUpDaoRead())
                # Update Method (setUpDaoUpdate())
                # Delete Method (setUpDaoDelete())
                # Vinyl Name Queue (setUpVinylNameQueue())
            + Create unit test for:
                # saveVinyl(Vinyl targetVinyl):
                    * Situations:
                        1) When I insert a valid vinyl, it adds to the database and returns the inserted vinyl: (Test 2.1)
                        2) When I insert a null vinyl, it throws an Exception: (Test 2.2)
                        3) When I insert a vinyl with blank catalog number, it throws an Exception: (Test 2.3)
                        4) When I insert a vinyl with an null catalog number, it throws an Exception: (Test 2.4)
                # getAllVinyls()
                    * Situations:
                        1) When database is empty, return empty list: (Test 1.1)
                        2) When database has one vinyl, return a list with that one vinyl: (Test 1.2)
                        3) When database has many vinyls, return a list with all those vinyls: (Test: 1.3)
                # getVinyl(String catalogNumber)
                    * Situations:
                        1) When catalog number is valid and vinyl in database, return vinyl: (Test 3.1)
                        2) When catalog number is null, throw exception: (Test 3.2)
                        3) When catalog number is valid but vinyl not in database, throw exception: (Test 3.3)
                # vinylNameQuery(String vinylName)
                    * Situations:
                        1) When one inputs a valid vinyl name query, return a list of possible vinyls: (Test 4.1)
                        2) When one inputs an invalid vinyl name query, throw exception: (Test 4.2)
                        3) When one inputs a empty vinyl name query, return empty collection: (Test 4.3)
                        4) When one inputs a valid vinyl name query but not in database: return empty collection: (Test 4.4)
                # editVinyl(String catalogNumber, Vinyl effectedVinyl)
                    * Situations:
                        1) When input is valid catalog number and vinyl, return the edited vinyl: (Test 5.1)
                        2) When input with invalid catalog number, throw exception: (Test 5.2)
                        3) When input with invalid vinyl, throw exception: (Test 5.3)
                # deleteVinyl(String catalogNumber)
                    * Situations:
                        1) When one inputs valid catalog number, return the deleted vinyl: (Test 6.1)
                        2) When one inputs an invalid catalog number, throw exception: (Test 6.2)
                        3) When one input valid catalog number but vinyl is not there, throw exception: (Test 6.3)
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
     * @throws Exception if the vinyl doesn't exist in database.
     */
    public Vinyl getVinyl(String catalogNumber) throws Exception {
        return vinylDao.read(catalogNumber).orElseThrow(Exception::new);
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
