package com.diazmic.VinylDatabaseAPI.db.dao.impl;

import com.diazmic.VinylDatabaseAPI.db.dao.IModelDao;
import com.diazmic.VinylDatabaseAPI.model.Artist;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;

import java.util.Collection;
import java.util.Optional;

public class ArtistDao implements IModelDao<Artist, Integer> {
    @Override
    public Artist create(Artist newModel) {
        return null;
    }

    @Override
    public Optional<Artist> read(Integer targetId) {
        return Optional.empty();
    }

    @Override
    public boolean update(Artist newModel, Integer targetId) {
        return false;
    }

    @Override
    public boolean delete(Integer targetId) {
        return false;
    }

    @Override
    public Collection<Artist> readAll() {
        return null;
    }

}
