package razepl.dev.mwobackend.api.book;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.mwobackend.entities.user.User;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {
    private final BookService bookService;

    @Override
    @GetMapping(value = "/getBooks")
    public final List<BookResponse> getBooks(@AuthenticationPrincipal User user) {
        return bookService.getBooks(user);
    }

    @Override
    @PostMapping(value = "/addNewBook")
    public final BookResponse addNewBook(@RequestParam("bookName") String bookName, @AuthenticationPrincipal User user) {
        return bookService.addNewBook(bookName, user);
    }

    @Override
    @DeleteMapping(value = "/removeBook")
    public final BookResponse removeBook(@RequestParam long bookId, @AuthenticationPrincipal User user) {
        return bookService.removeBook(bookId, user);
    }
}
