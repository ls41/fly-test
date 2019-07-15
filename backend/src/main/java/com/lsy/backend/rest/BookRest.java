package com.lsy.backend.rest;

import com.lsy.common.service.BookService;
import com.lsy.common.domain.Book;
import com.lsy.common.rest.AbstractRest;
import com.lsy.common.service.AbstractService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("admin/book")
public class BookRest extends AbstractRest<Book> {
    private final BookService bookService;

    public BookRest(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public Book create(@Valid @RequestBody Book book) {
        return super.create(book);
    }

    @Override
    @GetMapping
    public Book findById(@RequestParam @Min(0) Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/list")
    public List<Book> list(@RequestBody Book book) {
        return super.find(book);
    }

    @Override
    public AbstractService<Book> getService() {
        return bookService;
    }
}
