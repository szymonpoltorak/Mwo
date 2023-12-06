package razepl.dev.mwobackend.entities.book;

import org.mapstruct.Mapper;
import razepl.dev.mwobackend.api.book.BookResponse;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse bookToBookResponse(Book book);
}
