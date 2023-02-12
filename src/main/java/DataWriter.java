import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataWriter {
    File textFile = new File("word.txt");
    File jsonFile = new File("words.json");
    public void toTxt(List<String> words) {
        words = words.stream().sorted().distinct().collect(Collectors.toList());
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String word : words) {
                out.println(word);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!!!");
        }
    }

    public String[] fromTxt() throws IOException {
        String[] words = Files.readAllLines(textFile.toPath()).toArray(new String[0]);
        System.out.println("Слова восстановлены");
        return words;
    }

    public void toJsonFile(Map<String, List<PageEntry>> mapObjects) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("words.json")) {
            gson.toJson(mapObjects, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fromJsonFile(String word) {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try {
            Object obj = parser.parse(new FileReader(jsonFile));
            JSONObject jsonObject = (JSONObject) obj;
            jsonArray = (JSONArray) jsonObject.get(word);
            System.out.println(jsonArray);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonArray.toJSONString();
    }
}
