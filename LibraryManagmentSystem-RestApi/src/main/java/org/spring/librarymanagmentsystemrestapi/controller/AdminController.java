package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.spring.librarymanagmentsystemrestapi.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminOnly")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final AppUserService appUserService;
    private final BookService bookService;
    public AdminController(AppUserService appUserService, BookService bookService) {
        this.appUserService = appUserService;
        this.bookService = bookService;
    }
    @GetMapping("/getAllUsers")
    public List<AppUser> getAll() {
        return appUserService.getAllUsers();
    }
    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
    @PostMapping("/assignBook")
    public void assignBook(@RequestParam int userId, @RequestParam int bookId) {
        bookService.assignBookToUser(userId, bookId);
    }
    @PostMapping("/submitBook")
    public void submitBook( @RequestParam int userId, @RequestParam int bookId) {
        bookService.bookSubmission(userId, bookId);
    }
}
