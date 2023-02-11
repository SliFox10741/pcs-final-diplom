package OtherClass;

import java.io.IOException;
import java.util.*;

public class WorkWithMap {
    HashMap<String, List<PageEntry>> map = new HashMap<>();
    List<PageEntry> list;
    public void addObj(String word, PageEntry obj) {
        list = new ArrayList<>();
        list.add(obj);
        if (map.containsKey(word)) {
            map.get(word).add(obj);
        } else {
            map.put(word, list);
        }

    }
    public void end(List<String> list) throws IOException {
        DataWriter dataWriter = new DataWriter();
        String words[] = dataWriter.fromTxt();
        for (String word : words){
            this.list = map.get(word);
            Collections.sort(this.list);
            map.put(word, this.list);
        }
        dataWriter.toJsonFile(map);
        dataWriter.toTxt(list);
    }
}
