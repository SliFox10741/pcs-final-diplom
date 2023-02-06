import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonWritter {
    public void toJsonFile(Map<String, List<PageEntryBuilder>> mapObjects) {
        File fileJson = new File("words.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileJson)) {
            gson.toJson(mapObjects, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
