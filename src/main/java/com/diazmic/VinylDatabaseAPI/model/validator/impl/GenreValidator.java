package com.diazmic.VinylDatabaseAPI.model.validator.impl;

import com.diazmic.VinylDatabaseAPI.model.Genre;
import com.diazmic.VinylDatabaseAPI.model.validator.Validator;

public class GenreValidator implements Validator<Genre> {
    @Override
    public boolean validate(Genre targetModel) {
        return false;
    }
}
