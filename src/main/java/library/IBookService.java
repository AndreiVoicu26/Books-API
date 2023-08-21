package library;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IBookService {

    public Book addBook(Book book);

    public List<Book> getAllBooksSortedByAuthorAndTitle();

    public List<Book> getBooksByTitle(String author);

    public Book updateAuthorForBookWithTitle(String title, String author) throws NotFoundException;

    public boolean deleteBookWithTitle(String title);
}
