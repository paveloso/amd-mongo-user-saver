package com.teststation.mongousersaver.service.impl;

import com.teststation.mongousersaver.dao.PlayerRepository;
import com.teststation.mongousersaver.model.Player;
import com.teststation.mongousersaver.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    @Override
    public Player savePlayer(Player player) {
        return playerRepo.insert(player);
    }
}
