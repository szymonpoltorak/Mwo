package razepl.dev.mwobackend.api.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import razepl.dev.mwobackend.entities.book.Book;
import razepl.dev.mwobackend.entities.book.BookMapper;
import razepl.dev.mwobackend.entities.book.BookRepository;
import razepl.dev.mwobackend.entities.user.User;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public final List<BookResponse> getBooks(User user) {
        log.info("Getting all books");

        return bookRepository
                .findAllByUser(user)
                .stream()
                .map(bookMapper::bookToBookResponse)
                .toList();
    }

    @Override
    public final BookResponse addNewBook(String bookName, User user) {
        Book book = Book
                .builder()
                .name(bookName)
                .user(user)
                .build();

        log.info("Adding new book : {}", book);

        return bookMapper.bookToBookResponse(bookRepository.save(book));
    }

    @Override
    public final BookResponse removeBook(long bookId, User user) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found!"));

        log.info("Removing book: {}", book);

        bookRepository.deleteById(bookId);

        return bookMapper.bookToBookResponse(book);
    }
}
