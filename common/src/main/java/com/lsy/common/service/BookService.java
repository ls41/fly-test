package com.lsy.common.service;

import com.lsy.common.domain.Book;
import com.lsy.common.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing usrs.
 */
@Service
@Transactional
public class BookService extends AbstractService<Book> {

    private final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public JpaRepository<Book, Long> getRepository() {
        return this.bookRepository;
    }

    @Override
    public JpaSpecificationExecutor<Book> getExecutor() {
        return bookRepository;
    }

}
