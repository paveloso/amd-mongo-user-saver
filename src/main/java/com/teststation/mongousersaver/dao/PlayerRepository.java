package com.teststation.mongousersaver.dao;

import com.teststation.mongousersaver.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, Long> {

}
