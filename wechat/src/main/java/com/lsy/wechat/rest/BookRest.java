package com.lsy.wechat.rest;

import com.lsy.common.domain.Book;
import com.lsy.common.domain.Chapter;
import com.lsy.common.rest.AbstractRest;
import com.lsy.common.service.AbstractService;
import com.lsy.common.service.BookService;
import com.lsy.common.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("wx/book")
public class BookRest extends AbstractRest<Book> {
	private final BookService bookService;
	private final ChapterService chapterService;

	public BookRest(BookService bookService, ChapterService chapterService) {
		this.bookService = bookService;
		this.chapterService = chapterService;
	}

	@Override
	@GetMapping("find")
	public Book findById(@RequestParam @Min(0) Long id) {
		Book rtn = bookService.findById(id);
		rtn.setChapters(chapterService.findByExample(Chapter.builder().bookId(id).build()));
		return rtn;
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
