package com.dailycodework.sbemailverificationdemo.book;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookServiceImpl bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookDto> addTodo(@RequestBody BookDto todoDto){

        BookDto savedTodo = bookService.addBook(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllTodos(){
        List<BookDto> todos = bookService.getAllBooks();

        return ResponseEntity.ok(todos);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Integer id){
        BookDto todoDto = bookService.getBook(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer id){
        BookDto updatedTodo = bookService.updateBook(bookDto, id);
        return ResponseEntity.ok(updatedTodo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("{id}/borrow")
    public ResponseEntity<BookDto> borrowBook(@PathVariable("id") Integer id){
        BookDto updatedBook = bookService.borrowBook(id);
        return ResponseEntity.ok(updatedBook);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("{id}/return")
    public ResponseEntity<BookDto> returnBook(@PathVariable("id") Integer id){
        BookDto updatedTodo = bookService.returnBook(id);
        return ResponseEntity.ok(updatedTodo);
    }


}
