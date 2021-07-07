package com.diazmic.VinylDatabaseAPI.db.dao.impl;

import com.diazmic.VinylDatabaseAPI.db.dao.IModelDao;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.VinylInventory;

import java.util.Collection;
import java.util.Optional;

public class VinylInventoryDao implements IModelDao<VinylInventory,String> {

    @Override
    public VinylInventory create(VinylInventory newModel) {
        return null;
    }

    @Override
    public Optional<VinylInventory> read(String targetId) {
        return Optional.empty();
    }

    @Override
    public boolean update(VinylInventory newModel, String targetId) {
        return false;
    }

    @Override
    public boolean delete(String targetId) {
        return false;
    }

    @Override
    public Collection<VinylInventory> readAll() {
        return null;
    }
}
