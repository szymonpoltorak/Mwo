package razepl.dev.mwobackend.entities.book;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import razepl.dev.mwobackend.api.book.BookResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T18:32:55+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookResponse bookToBookResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        long bookId = 0L;
        String name = null;

        bookId = book.getBookId();
        name = book.getName();

        BookResponse bookResponse = new BookResponse( bookId, name );

        return bookResponse;
    }
}
