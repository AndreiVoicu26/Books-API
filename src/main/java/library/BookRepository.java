package library;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthor(String author);

    Book findByTitle(String title);
}
