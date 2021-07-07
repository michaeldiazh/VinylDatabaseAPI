package com.diazmic.VinylDatabaseAPI.model.validator.impl;

import com.diazmic.VinylDatabaseAPI.model.Artist;
import com.diazmic.VinylDatabaseAPI.model.validator.Validator;

public class ArtistValidator implements Validator<Artist> {
    @Override
    public boolean validate(Artist targetModel) {
        return false;
    }
}
