import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
//        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
//        System.out.println(engine.search("бизнес"));

        List<String> a = new ArrayList<>();
        File pdf = new File("pdfs/1. DevOps_MLops.pdf");
        var doc = new PdfDocument(new PdfReader(pdf));


        String text;
        String[] words;
        int pages = doc.getNumberOfPages();
        for (int i = 1; i <= pages; i++){
            var page = doc.getPage(i);
            text = PdfTextExtractor.getTextFromPage(page);
            words = text.split("\\P{IsAlphabetic}+");
            for (String word : words) {
                if (!a.contains(word.toLowerCase())) {
                    a.add(word.toLowerCase());
                }
            }
        }
        System.out.println(a);

        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате
    }
}