package com.teststation.mongousersaver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.teststation.mongousersaver.model.Player;
import com.teststation.mongousersaver.util.PlayerDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class RabbitMqListener implements MessageListener {

    @Autowired
    private PlayerService playerService;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final SimpleModule module = new SimpleModule();

    @Override
    public void onMessage(Message message) {
        log.info("message received: " + new String(message.getBody()));
        try {
            module.addDeserializer(Player.class, new PlayerDeserializer());
            jsonMapper.registerModule(module);
            Player player = jsonMapper.readValue(new String(message.getBody()), Player.class);
            playerService.savePlayer(player);
            log.info("player saved with id: " + player.getId() + ", and time: " + player.getCreationDate());
        } catch (IOException e) {
            log.error("error while parsing a message: " + new String(message.getBody()));
        }
    }
}
