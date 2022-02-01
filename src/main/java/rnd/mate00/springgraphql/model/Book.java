package rnd.mate00.springgraphql.model;

public class Book {

    private final String id;

    private final String title;

    private final int numPages;

    private BookTheme theme;

    public Book(String id, String title, int numPages, BookTheme theme) {
        this.id = id;
        this.title = title;
        this.numPages = numPages;
        this.theme = theme;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumPages() {
        return numPages;
    }

    public BookTheme getTheme() {
        return theme;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", numPages=" + numPages +
                ", theme=" + theme +
                '}';
    }
}
