package com.lsy.backend.rest;

import com.lsy.common.service.ProblemService;
import com.lsy.common.domain.Problem;
import com.lsy.common.rest.AbstractRest;
import com.lsy.common.service.AbstractService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("admin/problem")
public class ProblemRest extends AbstractRest<Problem> {
	private final ProblemService problemService;

	public ProblemRest(ProblemService problemService) {
		this.problemService = problemService;
	}

	@PostMapping("/create")
	public Problem create(@RequestBody Problem problem) {
		return super.create(problem);
	}

	@PostMapping("/createByString")
	public List<Problem> createByString(@RequestBody String s, Long bookId, Long chapterId) {
		return problemService.createByString(s, bookId, chapterId);
	}

	@PostMapping("/list")
	public List<Problem> list(@RequestBody Problem problem) {
		return super.find(problem);
	}

	@Override
	public AbstractService<Problem> getService() {
		return problemService;
	}

	@Override
	@GetMapping
	public Problem findById(@RequestParam @Min(0) Long id) {
		return problemService.findById(id);
	}
}
