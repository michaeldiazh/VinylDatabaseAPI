package com.diazmic.VinylDatabaseAPI.model.factory;

import com.diazmic.VinylDatabaseAPI.model.*;

import java.util.Collection;

public class VinylFactory {

    public static Vinyl makeVinyl(String catalogNumber, String title, Artist artist, Vinyl.Type type , Vinyl.Size size,
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

    public static Vinyl makeVinyl(String catalogNumber, String title, Genre genre, Artist artist, Vinyl.Type type, Vinyl.Size size,
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

    public static Vinyl makeVinyl(String catalogNumber, String title, Artist artist, Label label, Vinyl.Type type, Vinyl.Size size,
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

    public static Vinyl makeVinyl(String catalogNumber, String title, Genre genre, Artist artist,Label label, Vinyl.Type type, Vinyl.Size size,
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
}
