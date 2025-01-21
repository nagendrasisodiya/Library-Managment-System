package org.spring.librarymanagmentsystemrestapi.service;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.repository.AppUserRepo;
import org.spring.librarymanagmentsystemrestapi.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AppUserRepo appUserRepo;

    public BookService(BookRepo bookRepo , AppUserRepo appUserRepo) {
        this.bookRepo = bookRepo;
        this.appUserRepo = appUserRepo;
    }

    public void addBook(Book book) {
        int i=book.getQuantity();
        book.setAvailable(i);
        bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    //assigning a book to a user
    public void assignBookToUser(Integer userId, Integer bookId) {
        AppUser appUser = appUserRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book Not Found"));
        if(book.getAvailable()>0){
            book.setAvailable(book.getAvailable()-1);
            appUser.getBooks().add(book);
            bookRepo.save(book);
            appUserRepo.save(appUser);
        }else{
            throw new RuntimeException("Book Not Avialabile");
        }
    }
    //submit a book
    public void bookSubmission(Integer userId, Integer bookId) {
        System.out.println("calles for submissios");

        AppUser appUser=appUserRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book Not Found"));
        book.setAvailable(book.getAvailable()+1);
        bookRepo.save(book);
        appUser.getBooks().remove(book);
        appUserRepo.save(appUser);
    }
}
