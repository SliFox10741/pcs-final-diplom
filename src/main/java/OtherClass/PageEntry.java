package OtherClass;

public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final Integer page;
    private final Integer count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    public String getPdfName() {
        return pdfName;
    }

    public int getCount() {
        return count;
    }

    public int getPage() {
        return page;
    }

    @Override
    public int compareTo(PageEntry o) {
        int compareResult = o.count.compareTo(this.count);

        if (compareResult == 0) {
            compareResult = this.pdfName.compareTo(o.getPdfName());
        }

        if (compareResult == 0) {
            compareResult = (this.page).compareTo(o.getPage());
        }

        return compareResult;
    }

    // ???

    @Override
    public String toString() {
        return "\npdfName: " + pdfName +
                "\npage: " + page +
                "\ncount: " + count;
    }
}
