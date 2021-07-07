package com.diazmic.VinylDatabaseAPI.db.dao.impl;

import com.diazmic.VinylDatabaseAPI.db.dao.IModelDao;
import com.diazmic.VinylDatabaseAPI.model.Label;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;

import java.util.Collection;
import java.util.Optional;

public class LabelDao implements IModelDao<Label,Integer> {
    @Override
    public Label create(Label newModel) {
        return null;
    }

    @Override
    public Optional<Label> read(Integer targetId) {
        return Optional.empty();
    }

    @Override
    public boolean update(Label newModel, Integer targetId) {
        return false;
    }

    @Override
    public boolean delete(Integer targetModel) {
        return false;
    }

    @Override
    public Collection<Label> readAll() {
        return null;
    }
}
