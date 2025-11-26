import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Optional;

class GsonCommand {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String convertToGson(String message) {
        return gson.toJson(message);
    }

    String convertFromGson(String receiveMessage) {
        return gson.fromJson(receiveMessage,String.class);
    }

    void saveToFile(Users user) {
        try(FileWriter writer = new FileWriter(user.getName() + ".json"))
        {
            gson.toJson(user, writer);
            System.out.println("Save file");
        } catch (Exception e) {
            System.err.println("I can't create save file " + e.getMessage());
        }
    }

    Optional<Users> readFromFile(String nameFile) {

        try(FileReader reader = new FileReader(nameFile + ".json"))
        {
            return Optional.ofNullable(gson.fromJson(reader,Users.class));

        } catch (Exception e) {
            System.err.println("Can't read file in location " + e.getMessage());
        }
        return Optional.empty();
    }

}
