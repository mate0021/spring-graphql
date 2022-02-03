package rnd.mate00.springgraphql.model;

public class Book {

    private final String id;

    private final String title;

    private final String author;

    private final int numPages;

    private BookTheme theme;

    public Book(String id, String title, String author, int numPages, BookTheme theme) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.theme = theme;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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
                ", author='" + author + '\'' +
                ", numPages=" + numPages +
                ", theme=" + theme +
                '}';
    }
}
