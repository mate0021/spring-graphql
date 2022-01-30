package rnd.mate00.springgraphql.config;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import rnd.mate00.springgraphql.model.Book;

import java.util.List;

@Component
public class BookDataFetcher {

    private static List<Book> books = List.of(
            new Book("1", "book title 1"),
            new Book("2", "book 2"),
            new Book("3", "other book")
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
