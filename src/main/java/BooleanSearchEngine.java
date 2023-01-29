import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.bouncycastle.asn1.x500.DirectoryString;

import javax.swing.plaf.DesktopIconUI;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    //???
    List<String> wordList;

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы

        wordList = new ArrayList<>();

        File[] arrFiles = pdfsDir.listFiles();

        PdfDocument doc;
        String text;
        String[] words;
        PdfPage page;

        for (File file : arrFiles) {
            doc = new PdfDocument(new PdfReader(file));
            int pages = doc.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                page = doc.getPage(i);
                text = PdfTextExtractor.getTextFromPage(page);
                words = text.split("\\P{IsAlphabetic}+");
                for (String word : words) {
                    if (!wordList.contains(word.toLowerCase())) {
                        wordList.add(word.toLowerCase());
                    }
                }
            }
            wordList.add("\n");
        }

        System.out.println(wordList);

    }

    @Override
    public List<PageEntry> search(String word) {
        // тут реализуйте поиск по слову
        return Collections.emptyList();
    }
}
