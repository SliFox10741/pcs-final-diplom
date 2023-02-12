import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BooleanSearchEngine implements SearchEngine {

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы

        WorkWithMap workerMap = new WorkWithMap();
        PdfDocument doc;
        String[] words;
        PageEntryBuilder obj;
        List<String> sortedWords;
        Map<String, PageEntryBuilder> mapObjects = new HashMap<>();
        List<String> list = new ArrayList<>();

        for (File file : Objects.requireNonNull(pdfsDir.listFiles())) {
            doc = new PdfDocument(new PdfReader(file));
            for (int i = 1; i <= doc.getNumberOfPages(); i++) {
                words = PdfTextExtractor.getTextFromPage(doc.getPage(i))
                        .toLowerCase()
                        .split("\\P{IsAlphabetic}+");
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
                Collections.addAll(list, words);
                sortedWords = Stream.of(words).distinct().collect(Collectors.toList());
                for (String word : sortedWords) {
                    workerMap.addObj(word, mapObjects
                            .get(word)
                            .build());
                }
                mapObjects.clear();
            }
        }
        workerMap.end(list);
    }

    @Override
    public String search(String word) {
        DataWriter dataWriter = new DataWriter();
        String wordList = dataWriter.fromJsonFile(word);
        if (wordList == null) {
            throw new RuntimeException("Этого слова нет в файлах!!!");
        } else {
            return wordList;
        }
    }
}