package com.teststation.mongousersaver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.teststation.mongousersaver.model.Player;
import com.teststation.mongousersaver.util.PlayerDeserializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class RabbitMqListener implements MessageListener {

    private PlayerService playerService;

    private ObjectMapper jsonMapper = new ObjectMapper();

    private static final Logger LOGGER = Logger.getLogger(RabbitMqListener.class.getName());

    @Autowired
    public RabbitMqListener(final PlayerService playerService) {
        this.playerService = playerService;
    }

    public RabbitMqListener() {
    }

    @Override
    public void onMessage(Message message) {
        LOGGER.info("message received: " + new String(message.getBody()));
        try {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Player.class, new PlayerDeserializer());
            jsonMapper.registerModule(module);
            Player player = jsonMapper.readValue(new String(message.getBody()), Player.class);
            playerService.savePlayer(player);
            LOGGER.info("player saved with id: " + player.getId() + ", and time: " + player.getCreationDate());
        } catch (IOException e) {
            System.out.println(e);
            LOGGER.severe("error while parsing a message: " + new String(message.getBody()));
        }
    }
}
