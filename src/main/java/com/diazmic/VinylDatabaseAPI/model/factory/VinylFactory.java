package com.diazmic.VinylDatabaseAPI.model.factory;

import com.diazmic.VinylDatabaseAPI.model.*;

import java.util.Collection;
import java.util.Collections;

public class VinylFactory {
    public static final VinylFactory vinylFactory = new VinylFactory();

    public  Vinyl makeVinyl(String catalogNumber, String title, Artist artist, Vinyl.Type type , Vinyl.Size size,
                                  Vinyl.RPM rpm, Collection<Track> tracks, VinylInventory vinylInventory){
        return Vinyl.builder()
                .catalogNumber(catalogNumber)
                .title(title)
                .artist(artist)
                .type(type)
                .size(size)
                .rpm(rpm)
                .tracks(tracks)
                .vinylInventory(vinylInventory)
                .build();
    }

    public  Vinyl makeVinyl(String catalogNumber, String title, Genre genre, Artist artist, Vinyl.Type type, Vinyl.Size size,
                                  Vinyl.RPM rpm, Collection<Track> tracks, VinylInventory vinylInventory){
        return Vinyl.builder()
                .catalogNumber(catalogNumber)
                .title(title)
                .genre(genre)
                .artist(artist)
                .type(type)
                .size(size)
                .rpm(rpm)
                .tracks(tracks)
                .vinylInventory(vinylInventory)
                .build();
    }

    public  Vinyl makeVinyl(String catalogNumber, String title, Artist artist, Label label, Vinyl.Type type, Vinyl.Size size,
                                  Vinyl.RPM rpm, Collection<Track> tracks, VinylInventory vinylInventory){
        return Vinyl.builder()
                .catalogNumber(catalogNumber)
                .title(title)
                .label(label)
                .artist(artist)
                .type(type)
                .size(size)
                .rpm(rpm)
                .tracks(tracks)
                .vinylInventory(vinylInventory)
                .build();
    }

    public  Vinyl makeVinyl(String catalogNumber, String title, Genre genre, Artist artist,Label label, Vinyl.Type type, Vinyl.Size size,
                                  Vinyl.RPM rpm, Collection<Track> tracks, VinylInventory vinylInventory){
        return Vinyl.builder()
                .catalogNumber(catalogNumber)
                .title(title)
                .genre(genre)
                .artist(artist)
                .label(label)
                .type(type)
                .size(size)
                .rpm(rpm)
                .tracks(tracks)
                .vinylInventory(vinylInventory)
                .build();
    }

    public  Vinyl makeBlankVinyl(){
        return makeVinyl("","",new Artist(), Label.builder().build(), Vinyl.Type.LP, Vinyl.Size.TWELVE_INCH, Vinyl.RPM.FORTY_FIVE, Collections.emptyList(), VinylInventory.builder().build());
    }

    public  Vinyl makeTestVinyl(){
        VinylInventory testVinylInventory = VinylInventory.builder().vinylAmountInInventory(0).catalogNumber("00000000").build();
        Vinyl testVinyl = vinylFactory.makeBlankVinyl();
        testVinyl.setCatalogNumber("00000000");
        testVinyl.setGenre(Genre.ELECTRONIC);
        testVinyl.setTitle("testVinyl");
        testVinyl.setVinylInventory(testVinylInventory);
        return testVinyl;
    }
}
