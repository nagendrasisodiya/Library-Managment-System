package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.spring.librarymanagmentsystemrestapi.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final BookService bookService;
    private final AppUserService appUserService;
    public UserController(BookService bookService, AppUserService appUserService) {
        this.bookService = bookService;
        this.appUserService = appUserService;
    }
    //get all assigned book to a user by its id
    @GetMapping("/assignedBooks")
    public Set<Book> assignBooks(@AuthenticationPrincipal UserDetails userDetails) {
        AppUser user= appUserService.getUserByEmail(userDetails.getUsername());
        return user.getBooks();
    }

}
