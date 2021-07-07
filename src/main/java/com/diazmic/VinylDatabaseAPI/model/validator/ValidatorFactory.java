package com.diazmic.VinylDatabaseAPI.model.validator;

import com.diazmic.VinylDatabaseAPI.model.Artist;
import com.diazmic.VinylDatabaseAPI.model.Genre;
import com.diazmic.VinylDatabaseAPI.model.Label;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.ArtistValidator;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.GenreValidator;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.LabelValidator;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.VinylValidator;

public class ValidatorFactory {
    private static final Validator<Vinyl> vinylValidator = new VinylValidator();
    private static final Validator<Artist> artistValidator = new ArtistValidator();
    private static final Validator<Genre> genreValidator = new GenreValidator();
    private static final Validator<Label> labelValidator = new LabelValidator();

    public static Validator<Vinyl> bulidVinylValidator(){
        return vinylValidator;
    }
    public static Validator<Artist> bulidArtistValidator(){
        return artistValidator;
    }
    public static Validator<Genre> bulidGenreValidator(){
        return genreValidator;
    }
    public static Validator<Label> bulidLabelValidator(){
        return labelValidator;
    }
}
