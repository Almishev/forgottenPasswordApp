package com.dailycodework.sbemailverificationdemo.book;

import com.dailycodework.sbemailverificationdemo.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements IBookService {

    private BookRepository bookRepository;

    private ModelMapper modelMapper;


    @Override
    public BookDto addBook(BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);


        Book savedBook = bookRepository.save(book);


        return modelMapper.map(savedBook, BookDto.class);
    }


    @Override
    public BookDto getBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id:" + id));

        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> todos = bookRepository.findAll();

        return todos.stream().map((book) -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setYear(bookDto.getYear());
        book.setPublisher(bookDto.getPublisher());
        book.setAvailable(bookDto.isAvailable());

        Book updatedBook = bookRepository.save(book);

        return modelMapper.map(updatedBook, BookDto.class);
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id : " + id));

    }

    @Override
    public BookDto borrowBook(Integer id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id : " + id));

        if( book.isAvailable()==Boolean.TRUE) {
            book.setAvailable(Boolean.FALSE);

            Book updatedBook = bookRepository.save(book);

            return modelMapper.map(updatedBook, BookDto.class);

        }
        else{
            throw  new ResourceNotFoundException("This book is not available!!");
        }

    }

    @Override
    public BookDto returnBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id : " + id));

        if( book.isAvailable()==Boolean.FALSE) {
            book.setAvailable(Boolean.TRUE);

            Book updatedBook = bookRepository.save(book);

            return modelMapper.map(updatedBook, BookDto.class);

        }
        else{
            throw  new ResourceNotFoundException("This book is not borrowed!!");
        }



    }
}
