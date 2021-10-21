package com.diazmic.VinylDatabaseAPI.db.dao.impl;

import com.diazmic.VinylDatabaseAPI.db.repo.VinylRepository;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.VinylValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class VinylUpdater {
    private static VinylValidator vinylValidator = new VinylValidator();

    public static boolean updateVinyl(String targetVinylCatalogNumber, Vinyl newModel, VinylRepository vinylRepository) throws InvocationTargetException, IllegalAccessException {
        Optional<Vinyl> targetVinylBucket = vinylRepository.findById(targetVinylCatalogNumber);
        if(targetVinylBucket.isEmpty() || !vinylValidator.validate(newModel))
            return false;
        vinylRepository.save(newModel);
        return true;
    }
}
