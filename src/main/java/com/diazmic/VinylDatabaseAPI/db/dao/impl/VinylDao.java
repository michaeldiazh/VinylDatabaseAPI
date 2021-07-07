package com.diazmic.VinylDatabaseAPI.db.dao.impl;

import com.diazmic.VinylDatabaseAPI.db.dao.IModelDao;
import com.diazmic.VinylDatabaseAPI.db.repo.VinylRepoistory;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;


@Service
public class VinylDao implements IModelDao<Vinyl,String> {
    @Autowired
    private VinylRepoistory vinylRepoistory;

    @Override
    public Vinyl create(Vinyl newModel) {
        return null;
    }

    @Override
    public Optional<Vinyl> read(String targetId) {
        return Optional.empty();
    }

    @Override
    public boolean update(Vinyl newModel, String targetModel) {
        return false;
    }

    @Override
    public boolean delete(String targetId) {
        if (vinylRepoistory.findById(targetId).isPresent())
            return false;
        Vinyl vinyl = vinylRepoistory.findById(targetId).get();
        vinylRepoistory.delete(vinyl);
        return !vinylRepoistory.existsById(targetId);
    }

    @Override
    public Collection<Vinyl> readAll(){
        return vinylRepoistory.findAll();
    }

    public Collection<Vinyl> vinylNameQueue(String vinylName){
        if(vinylName.isBlank())
            throw new MongoException("Search input is blank, please put something for search (:");
        return vinylRepoistory.getVinylsByName("^"+vinylName+"$");
    }
}
