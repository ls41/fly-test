package com.lsy.common.service;

import com.lsy.common.domain.Problem;
import com.lsy.common.repository.ProblemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Service class for managing users.
 */
@Service
@Transactional
@Slf4j
public class ProblemService extends AbstractService<Problem> {

	private final ProblemRepository problemRepository;

	private final SelectionService selectionService;

	public ProblemService(ProblemRepository problemRepository, SelectionService selectionService) {
		this.problemRepository = problemRepository;
		this.selectionService = selectionService;
	}


	@Override
	public JpaRepository<Problem, Long> getRepository() {
		return this.problemRepository;
	}

	@Override
	public JpaSpecificationExecutor<Problem> getExecutor() {
		return problemRepository;
	}

	public List<Problem> findByChapterId(Long id) {
		List<Problem> storageList = this.findByExample(Problem.builder().chapterId(id).build());
		storageList.parallelStream().forEach(problem -> problem.setSelections(selectionService.findByProblemId(problem.getId())));
		return storageList;
	}

	public List<Problem> findByChapterIdIn(List<Long> ids) {
		return this.getExecutor().findAll((root, query, criteriaBuilder) -> {
					CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("chapterId"));
					ids.forEach(in::value);
					return query.where(in).getRestriction();
				}
		);

	}


}
