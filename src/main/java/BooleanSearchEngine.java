import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BooleanSearchEngine implements SearchEngine {
    //???
    JsonWritter jsonWritter;
    public BooleanSearchEngine(File pdfsDir) throws IOException {
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы

        A a = new A();

        jsonWritter = new JsonWritter();

        File[] arrFiles = pdfsDir.listFiles();

        PdfDocument doc;

        String[] words;
        PdfPage page;
        PageEntryBuilder obj;
        Map<String, PageEntry> preparedMap = new HashMap<>();
        List<String> s;
        Map<String, PageEntryBuilder> mapObjects = new HashMap<>();

        for (File file : arrFiles) {
            doc = new PdfDocument(new PdfReader(file));
            for (int i = 1; i <= doc.getNumberOfPages(); i++) {
                page = doc.getPage(i);
                words = PdfTextExtractor.getTextFromPage(page).toLowerCase().split("\\P{IsAlphabetic}+");
                for (String word : words) {
                    obj = mapObjects.get(word);
                    if (obj != null) {
                        if (obj.getPdfName().equals(file.getName())) {
                            if (obj.getPage() == i) {
                                obj.addCount();
                            } else {
                                mapObjects.put(word, new PageEntryBuilder().setPdfName(file.getName()).setPage(i));
                            }
                        } else {
                            mapObjects.put(word, new PageEntryBuilder().setPdfName(file.getName()).setPage(i));
                        }
                    } else {
                        mapObjects.put(word, new PageEntryBuilder().setPdfName(file.getName()).setPage(i));
                    }

                }

                s = Stream.of(words).distinct().collect(Collectors.toList());

//                System.out.println(words.length);
//                System.out.println(s.size());

                for (String word : s) {
                    a.addObj(word, mapObjects);
//                    preparedMap.put(word, mapObjects.get(word).build());
//                    System.out.println(word + "\n" + preparedMap.get(word) + "\n");
                }

            mapObjects.clear();
            }
//            break;
        }
//        a.lookAll();
//        jsonWritter.toJsonFile(mapObjects);
//        for (int i = 0; i < mapObjects.size(); i++) {
//            mapObjects.get("бизнес").;
//        }
        a.end();
    }

    @Override
    public List<PageEntry> search(String word) {
        // тут реализуйте поиск по слову
        return Collections.emptyList();
    }
}
