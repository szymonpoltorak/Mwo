package razepl.dev.mwobackend.api.book;

import razepl.dev.mwobackend.entities.book.Book;
import razepl.dev.mwobackend.entities.user.User;

import java.util.List;

public interface BookService {
    List<BookResponse> getBooks(User user);

    BookResponse addNewBook(String bookName, User user);

    BookResponse removeBook(long bookId, User user);
}
