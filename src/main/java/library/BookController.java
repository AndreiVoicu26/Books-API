package library;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooksSortedByAuthorAndTitle() {
        try {
            List<Book> books = bookService.getAllBooksSortedByAuthorAndTitle();
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Books fetching failed");
        }
    }

    @GetMapping("/{author}")
    public ResponseEntity<?> getBooksByTitle(@PathVariable String author) {
        try {
            List<Book> books = bookService.getBooksByTitle(author);
            return books.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found with the author: " + author)
                    : ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Books fetching failed");
        }
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book newBook = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book adding failed");
        }
    }

    @PatchMapping("/{title}")
    public ResponseEntity<?> updateAuthorForBookWithTitle(@PathVariable String title, @RequestBody String author) {
        try {
            Book book = bookService.updateAuthorForBookWithTitle(title, author);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found with the title: " + title);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book updating failed");
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteBookWithTitle(@PathVariable String title) {
        try {
            boolean deletedStatus = bookService.deleteBookWithTitle(title);
            return deletedStatus
                    ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found with the title: " + title);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book deleting failed");
        }
    }
}
