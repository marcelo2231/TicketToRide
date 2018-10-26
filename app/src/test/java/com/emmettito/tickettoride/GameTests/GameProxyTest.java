package com.emmettito.tickettoride.GameTests;

import com.emmettito.models.CommandModels.GameCommands.ChatRequest;
import com.emmettito.models.Results.ChatResult;
import com.emmettito.tickettoride.communication.proxy.GameProxy;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertTrue;

public class GameProxyTest {
    GameProxy proxy;
    Gson gson;

    @Before
    public void setUp() {
        proxy = new GameProxy();
        gson = new Gson();
    }

    @Test
    public void testSendMessageInvalid() throws FileNotFoundException {
        ChatRequest request = new ChatRequest();
        request.setGameName("Game");
        request.setMessage("This is the message");
        request.setPlayerName("Player 1");

        JsonReader reader = new JsonReader(new FileReader("app/src/test/java/com/emmettito/tickettoride/GameTests/InvalidChatTest.json"));

        ChatResult expectedResponse = gson.fromJson(reader, ChatResult.class);

        ChatResult result = proxy.sendChatMessage(request);

        System.out.println(gson.toJson(expectedResponse));
        System.out.println(gson.toJson(result));

        assertTrue(!(gson.toJson(result).equals(gson.toJson(expectedResponse))));
        //assertTrue(1 == 1);
        //assertTrue(request.getGameName().equals("Game"));
    }

}