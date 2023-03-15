package com.example.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LibraryService {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooksSortedByAuthorAndTitle() {
        Comparator<Book> byAuthorThenTitle = Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle);
        List<Book> sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks, byAuthorThenTitle);
        return sortedBooks;
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public void updateAuthor(Book book, String author) {
        book.setAuthor(author);
    }
}