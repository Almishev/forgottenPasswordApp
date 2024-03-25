package com.dailycodework.sbemailverificationdemo.book;

import java.util.List;

public interface IBookService  {

    BookDto addBook(BookDto bookDto);

    BookDto getBook(Integer id);

    List<BookDto> getAllBooks();

    BookDto updateBook(BookDto BookDto, Integer id);

    void deleteBook(Integer id);

    BookDto borrowBook(Integer id);

    BookDto returnBook(Integer id);


}
