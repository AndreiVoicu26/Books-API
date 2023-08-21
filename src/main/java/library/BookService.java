package library;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.insert(book);
    }

    public List<Book> getAllBooksSortedByAuthorAndTitle() {
        return bookRepository.findAll(Sort.by(
                Sort.Order.asc("author"),
                Sort.Order.asc("title")));
    }

    public List<Book> getBooksByTitle(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books != null ? books : Collections.emptyList();
    }

    public Book updateAuthorForBookWithTitle(String title, String author) throws NotFoundException {
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            throw new NotFoundException();
        }
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public boolean deleteBookWithTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            return false;
        }
        bookRepository.delete(book);
        return true;
    }
}