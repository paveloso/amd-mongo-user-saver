package com.teststation.mongousersaver.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.teststation.mongousersaver.model.Player;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

public class PlayerDeserializer extends StdDeserializer<Player> {

    public PlayerDeserializer() {
        this(null);
    }

    public PlayerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Player deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String root = jsonParser.getText();
        JSONObject playerJsonObject = new JSONObject(root);

        return new Player(
                playerJsonObject.getString("id"),
                playerJsonObject.getString("name"),
                playerJsonObject.getString("country"),
                playerJsonObject.getInt("age"),
                new Date(playerJsonObject.getLong("creationDate"))
        );
    }
}
