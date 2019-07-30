package com.lsy.wechat.rest;

import com.lsy.common.domain.Chapter;
import com.lsy.common.domain.Problem;
import com.lsy.common.domain.Selection;
import com.lsy.common.service.ChapterService;
import com.lsy.common.service.ProblemService;
import com.lsy.common.service.SelectionService;
import com.lsy.wechat.config.LoginUser;
import com.lsy.wechat.domain.dto.LastVisitDto;
import com.lsy.wechat.service.LearnRecordService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("wx/chapter")
public class ChapterLearnRest {

	private final ChapterService chapterService;
	private final ProblemService problemService;
	private final SelectionService selectionService;
	private final LearnRecordService learnRecordService;

	public ChapterLearnRest(ChapterService chapterService, ProblemService problemService, SelectionService selectionService, LearnRecordService learnRecordService) {
		this.chapterService = chapterService;
		this.problemService = problemService;
		this.selectionService = selectionService;
		this.learnRecordService = learnRecordService;
	}

	@GetMapping("/learn")
	public Chapter learn(@RequestParam @NotNull Long chapterId) {
		Chapter storage = this.chapterService.findById(chapterId);
		List<Problem> problems = problemService.findByChapterId(chapterId);
		//TODO add cache
		problems.parallelStream().forEach(problem ->
				problem.setSelections(
						selectionService.findByExample(Selection.builder().problemId(problem.getId()).build())
				)
		);
		storage.setProblems(problems);
		return storage;
	}


	@PostMapping("/lastVisit")
	public void createLastVisit(@LoginUser Long userId, Long problemId) {
		this.learnRecordService.createLastVisitData(userId,problemId);
	}


	@GetMapping("/lastVisit")
	public LastVisitDto getLastVisit(@LoginUser Long userId) {
		return this.learnRecordService.getVisitData(userId);
	}

	@GetMapping("/index")
	public List<Chapter> findByBookId(@RequestParam @NotNull Long bookId) {
		return this.chapterService.findByExample(Chapter.builder().bookId(bookId).build());
	}
}
