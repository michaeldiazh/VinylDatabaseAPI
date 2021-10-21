package com.diazmic.VinylDatabaseAPI.db.dao.impl;

import com.diazmic.VinylDatabaseAPI.db.dao.IModelDao;
import com.diazmic.VinylDatabaseAPI.db.repo.VinylRepository;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.VinylValidator;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;

/*
    TODO:
        + Create Unit Test and Build the methods for the Dao:
            # create(Vinyl newModel)
                1) If I create a invalid model (fails Validator), throw exception: (Test 1.1)
                2) If I create a model that existent in the database, return vinyl:
                3) If I create a valid model, return the vinyl:
            # read(String targetId)
                1) If the targetId is null, throw exception:
                2) If the targetId is valid, return the valid vinyl:
                3) If the targetId is valid but not in database, throw exception:
            # update(Vinyl newModel, String targetModel)
                1) If one can update a vinyl, return true:
                2) If one can not update a vinyl, return false:
            # delete(String targetId)
                1) When it is valid and in database, delete and return true:
                2) When it is valid and in database, but failed the validator, return false:
            # readAll()
                1) When I call readAll() and database is empty, return a empty collection:
                2) When I call readAll() and database is not empty, return filled collect:
            # vinylNameQueue(String vinylName)
                1) Return a collection of Vinyls from the vinylName Queue
*/
@Service
public class VinylDao implements IModelDao<Vinyl,String> {
    @Autowired
    private VinylRepository vinylRepository;

    @Autowired
    private VinylValidator vinylValidator;

    @Override
    public Vinyl create(Vinyl newModel) throws InvocationTargetException, IllegalAccessException {
        if(vinylValidator.validate(newModel))
            return null;
        return vinylRepository.findById(newModel.getCatalogNumber())
                .orElseGet(() ->
                        vinylRepository.save(newModel));
    }

    @Override
    public Optional<Vinyl> read(String targetId) {
        Optional<Vinyl> targetVinylBucket = vinylRepository.findById(targetId);
        if(targetVinylBucket.isEmpty())
            return Optional.empty();
        return targetVinylBucket;
    }

    @Override
    public boolean update(Vinyl newModel, String targetModel) throws InvocationTargetException, IllegalAccessException {
        return VinylUpdater.updateVinyl(targetModel,newModel,vinylRepository);
    }

    @Override
    public boolean delete(String targetId) {
        if (vinylRepository.findById(targetId).isPresent())
            return false;
        Vinyl vinyl = vinylRepository.findById(targetId).get();
        vinylRepository.delete(vinyl);
        return !vinylRepository.existsById(targetId);
    }

    @Override
    public Collection<Vinyl> readAll(){
        return vinylRepository.findAll();
    }

    public Collection<Vinyl> vinylNameQueue(String vinylName){
        if(vinylName.isBlank())
            throw new MongoException("Search input is blank, please put something for search (:");
        return vinylRepository.getVinylsByName("^"+vinylName+"$");
    }


}
