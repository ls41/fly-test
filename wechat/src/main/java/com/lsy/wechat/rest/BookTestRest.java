package com.lsy.wechat.rest;

import com.lsy.common.domain.Chapter;
import com.lsy.common.domain.Problem;
import com.lsy.common.domain.Selection;
import com.lsy.common.service.ChapterService;
import com.lsy.common.service.ProblemService;
import com.lsy.common.service.SelectionService;
import com.lsy.wechat.utils.TestRandomUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("wx/test")
public class BookTestRest {

	private final ChapterService chapterService;
	private final ProblemService problemService;
	private final SelectionService selectionService;

	public BookTestRest(ChapterService chapterService, ProblemService problemService, SelectionService selectionService) {
		this.chapterService = chapterService;
		this.problemService = problemService;
		this.selectionService = selectionService;
	}


	@GetMapping
	public List<Problem> learn(@RequestParam @NotNull Long bookId) {
		List<Problem> storage = problemService.findByChapterIdIn(chapterService.findByExample(Chapter.builder().bookId(bookId).build()).stream().map(Chapter::getId).collect(Collectors.toList()));
		Set<Integer> set = TestRandomUtil.get(3, storage.size());
		List<Problem> rtn = new ArrayList<>();
		set.forEach(integer -> {
			storage.get(integer).setSelections(
					selectionService.findByExample(Selection.builder().problemId(storage.get(integer).getId()).build())
			);
			rtn.add(storage.get(integer));
		});
		return rtn;
	}

	@GetMapping("/index")
	public List<Chapter> findByBookId(@RequestParam @NotNull Long bookId) {
		return this.chapterService.findByExample(Chapter.builder().bookId(bookId).build());
	}
}
