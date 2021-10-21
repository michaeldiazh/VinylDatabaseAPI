package com.diazmic.VinylDatabaseAPI.db.repo;

import com.diazmic.VinylDatabaseAPI.model.Artist;
import com.diazmic.VinylDatabaseAPI.model.Genre;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface VinylRepository extends MongoRepository<Vinyl, String> {

    /**
     * Get a Vinyl from the MongoDB database based on name.
     * @param name {@code String}
     * @return target {@code Vinyl}
     */
    @Query("{'name' : ?0}")
    Collection<Vinyl> getVinylsByName(String name);

    @Query("{'genre' : ?0}")
    Collection<Vinyl> getVinylsByGenre(Genre genre);

    @Query("{'artist' : ?0}")
    Collection<Vinyl> getVinylsByArtist(Artist artist);

    @Query("{'name': { $regex : ?0 }}")
    Collection<Vinyl> getVinylByNameRegex(String name);


}
