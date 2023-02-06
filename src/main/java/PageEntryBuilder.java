public class PageEntryBuilder {
    private String pdfName;
    private int page;
    private int count = 1;
    public PageEntryBuilder() {
    }

    public PageEntryBuilder setPdfName(String pdfName) {
        this.pdfName = pdfName;
        return this;
    }
    public PageEntryBuilder setPage(int page) {
        this.page = page;
        return this;
    }
    public PageEntryBuilder setCount(int count) {
        this.count = count;
        return this;
    }
    public void addCount() {
        count++;
    }
    public PageEntry build() {
        PageEntry pageEntry = new PageEntry(pdfName, page, count);
        return new PageEntry(pdfName, page, count);
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public String getPdfName() {
        return pdfName;
    }

    @Override
    public String toString() {
        return "[ \npdfName: " + pdfName +
                "\npage: " + page +
                "\ncount: " + count + "\n]";
    }
}
