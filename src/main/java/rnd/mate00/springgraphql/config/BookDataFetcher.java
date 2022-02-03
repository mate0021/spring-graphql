package rnd.mate00.springgraphql.config;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import rnd.mate00.springgraphql.model.Book;

import java.util.List;

import static rnd.mate00.springgraphql.model.BookTheme.*;

@Component
public class BookDataFetcher {

    private static List<Book> books = List.of(
            new Book("1", "book title 1", "misza1", 138, HORROR),
            new Book("2", "book 2", "misza2", 200, SPORTS),
            new Book("3", "other book", "misza3", 210, FINANCE)
    );

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
}
