package com.lsy.wechat.rest;

import com.lsy.common.domain.Chapter;
import com.lsy.common.domain.Problem;
import com.lsy.common.domain.Selection;
import com.lsy.common.service.ChapterService;
import com.lsy.common.service.ProblemService;
import com.lsy.common.service.SelectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("wx/chapter")
public class ChapterLearnRest {

	private final ChapterService chapterService;
	private final ProblemService problemService;
	private final SelectionService selectionService;

	public ChapterLearnRest(ChapterService chapterService, ProblemService problemService, SelectionService selectionService) {
		this.chapterService = chapterService;
		this.problemService = problemService;
		this.selectionService = selectionService;
	}

	@GetMapping("/learn")
	public Chapter learn(@RequestParam @NotNull Long chapterId) {
		Chapter storage = this.chapterService.findById(chapterId);
		List<Problem> problems = problemService.findByChapterId(chapterId);
		//TODO add cache
		problems.stream().parallel().forEach(problem ->
				problem.setSelections(
						selectionService.findByExample(Selection.builder().problemId(problem.getId()).build())
				)
		);
		storage.setProblems(problems);
		return storage;
	}

	@GetMapping("/index")
	public List<Chapter> findByBookId(@RequestParam @NotNull Long bookId) {
		return this.chapterService.findByExample(Chapter.builder().bookId(bookId).build());
	}
}
