package com.lsy.wechat.rest;

import com.lsy.common.domain.Chapter;
import com.lsy.common.domain.Problem;
import com.lsy.common.domain.Selection;
import com.lsy.common.domain.wx.TestDetailRecord;
import com.lsy.common.domain.wx.TestRecord;
import com.lsy.common.repository.wx.TestDetailRecordRepository;
import com.lsy.common.service.ChapterService;
import com.lsy.common.service.ProblemService;
import com.lsy.common.service.SelectionService;
import com.lsy.wechat.config.LoginUser;
import com.lsy.wechat.domain.dto.TestResultDto;
import com.lsy.wechat.service.TestRecordService;
import com.lsy.wechat.utils.TestRandomUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("wx/test")
public class TestRest {

	private final ChapterService chapterService;
	private final ProblemService problemService;
	private final SelectionService selectionService;
	private final TestRecordService testRecordService;
	private final TestDetailRecordRepository testDetailRecordRepository;

	public TestRest(ChapterService chapterService, ProblemService problemService, SelectionService selectionService, TestRecordService testRecordService, TestDetailRecordRepository testDetailRecordRepository) {
		this.chapterService = chapterService;
		this.problemService = problemService;
		this.selectionService = selectionService;
		this.testRecordService = testRecordService;
		this.testDetailRecordRepository = testDetailRecordRepository;
	}


	@GetMapping
	public List<Problem> test(@LoginUser Long userId, @RequestParam @NotNull Long bookId) {
		List<Problem> storage = problemService.findByChapterIdIn(chapterService.findByExample(Chapter.builder().bookId(bookId).build()).stream().map(Chapter::getId).collect(Collectors.toList()));
		Set<Integer> set = TestRandomUtil.get(100, storage.size());
		List<Problem> rtn = new ArrayList<>();
		TestRecord testRecord = testRecordService.createByWXUserId(userId);
		set.forEach(integer -> {
			storage.get(integer).setSelections(
					selectionService.findByExample(Selection.builder().problemId(storage.get(integer).getId()).build())
			);
			rtn.add(storage.get(integer));
		});

		return rtn;
	}

	@PostMapping("/submit")
	public TestResultDto submit(@LoginUser Long userId, @RequestBody List<Selection> selections) {
		TestRecord testRecord = testRecordService.findLastOne(userId);
		List<Selection> storage = selectionService.findAllByIds(selections.stream().map(Selection::getId).collect(Collectors.toList()));
		List<TestDetailRecord> toSave = new ArrayList<>();
		storage.forEach(selection ->
				toSave.add(TestDetailRecord.builder()
						.problemId(selection.getProblemId())
						.testRecordId(testRecord.getId())
						.selectionId(selection.getId())
						.build()));
		this.testDetailRecordRepository.saveAll(toSave);
		long correct = storage.stream().filter(Selection::getCorrect).count();
		return TestResultDto.builder()
				.error(((short) correct))
				.score((short) (100 - correct))
				.build();
	}

}
