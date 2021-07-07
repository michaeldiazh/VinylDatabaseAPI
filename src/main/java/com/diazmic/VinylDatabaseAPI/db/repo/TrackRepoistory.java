package com.diazmic.VinylDatabaseAPI.db.repo;

import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepoistory extends MongoRepository<Vinyl,String> {

}
