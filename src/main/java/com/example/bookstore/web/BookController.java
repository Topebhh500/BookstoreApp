package com.example.bookstore.web;

import com.example.bookstore.domain.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Display the main book list page for both "/" and "/booklist"
    @GetMapping({"/", "/booklist"})
    public String showBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";  // Renders booklist.html
    }

    // Show the form for adding a new book
    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";  // Renders addbook.html
    }

    // Handle form submission for adding a new book
    @PostMapping("/addbook")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist"; 
    }

    // Show the form for editing an existing book
    @GetMapping("/editbook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "editbook"; 
    }

    // Handle form submission for editing a book
    @PostMapping("/editbook/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    // Handle book deletion
    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
}
