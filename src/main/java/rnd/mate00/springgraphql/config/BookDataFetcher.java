package rnd.mate00.springgraphql.config;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import rnd.mate00.springgraphql.model.Book;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static rnd.mate00.springgraphql.model.BookTheme.*;

@Component
public class BookDataFetcher {

    private static final List<Book> books = new ArrayList<>();

    @PostConstruct
    void init() {
        books.add(new Book("1", "book title 1", "misza1", 138, HORROR));
        books.add(new Book("2", "book 2", "misza2", 200, SPORTS));
        books.add(new Book("3", "other book", "misza3", 210, FINANCE));
    }


    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books.stream()
                    .filter(book -> book.getId().equalsIgnoreCase(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher<List<Book>> allBooks() {
        return dataFetchingEnvironment -> books;
    }

    public DataFetcher<Book> addBook() {
        return dataFetching -> {
            System.out.println("" + dataFetching.getArgument("book"));
            LinkedHashMap<String, Object> bookArg = dataFetching.getArgument("book");
            Book newBook = new Book((String) bookArg.get("id"), (String) bookArg.get("title"), (String) bookArg.get("author"), (int) bookArg.get("numPages"), REPORT);
            books.add(newBook);
            
            return newBook;
        };
    }
}
