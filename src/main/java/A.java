import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A {
    List<PageEntryBuilder> list;
    HashMap<String, List<PageEntryBuilder>> map = new HashMap<>();

    public void addObj(String word, Map<String, PageEntryBuilder> obj) {
        list = new ArrayList<>();
        list.add(obj.get(word));
        if (map.containsKey(word)) {
            map.get(word).add(obj.get(word));
        } else {
            map.put(word, list);
        }

    }
//    public void lookAll() {
//        for (int i = 0; i < map.size(); i++) {
//            System.out.println(map);
//        }
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for(String word : map.keySet()){
//            for (int i = 0; i < map.get(word).size(); i++)
//                stringBuilder.append('{' + "\n" +
//                    map.get(word).get(i) + "\n" +
//                    '}' + "\n");
//        }
//        return stringBuilder.toString();
//    }

    public void end() {
        JsonWritter jsonWritter = new JsonWritter();
        jsonWritter.toJsonFile(map);
    }
    //    public boolean addMap(HashMap<String, PageEntryBuilder> map) {
//        list.get(map.keySet()).add(map.get(map.keySet()));
//        return true;
//    }

//    public HashMap<String, List<PageEntryBuilder>> getList() {
//        return list;
//    }
}
