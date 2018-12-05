import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.Base64;

public class Serializer {

    public static String serialize(Object object) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(bytes);

        objectStream.writeObject(object);

        return (Base64.getEncoder().encodeToString(bytes.toByteArray()));
    }

    public static Object deserialize(String serializedString) throws IOException {
        byte[] data = Base64.getDecoder().decode(serializedString);

        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(data));

        try {
            return stream.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}


    /**
     *
     *      Example of how to use the Serializer class
     *
     */

    /*
    Game game = new Game();

    System.out.println(game);

    String serializedGame = new Serializer().serialize(game);

    System.out.println(serializedGame);

    ByteArrayInputStream stream = new ByteArrayInputStream(serializedGame.getBytes(StandardCharsets.UTF_8));

    try {
        Game thisGame = (Game) new Serializer().deserialize(stream, Game.class);
        System.out.println(thisGame.toString());
    } catch (java.lang.Exception e) {
        e.printStackTrace();
    }
    */