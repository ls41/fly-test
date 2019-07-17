package com.lsy.common.service;

import com.lsy.common.domain.Book;
import com.lsy.common.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing usrs.
 */
@Service
@Transactional
@Slf4j
public class BookService extends AbstractService<Book> {

	private final BookRepository bookRepository;

	private final ChapterService chapterService;


	public BookService(BookRepository bookRepository, ChapterService chapterService) {
		this.bookRepository = bookRepository;
		this.chapterService = chapterService;
	}

	public Book findByIdWithChapters(Long id) {
		Book storage = this.bookRepository.findById(id).orElseThrow(RuntimeException::new);
		storage.setChapters(this.chapterService.findByBookId(storage.getId()));
		return storage;
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
