package com.diazmic.VinylDatabaseAPI.db.repo;

import com.diazmic.VinylDatabaseAPI.model.VinylInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinylInventoryRepository extends MongoRepository<VinylInventory, String> {

}
