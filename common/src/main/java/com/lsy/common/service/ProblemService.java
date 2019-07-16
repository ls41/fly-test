package com.lsy.common.service;

import com.lsy.common.domain.Problem;
import com.lsy.common.repository.ProblemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProblemService extends AbstractService<Problem> {

	private final Logger log = LoggerFactory.getLogger(ProblemService.class);

	private final ProblemRepository problemRepository;


	public ProblemService(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
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
		return this.findByExample(Problem.builder().chapterId(id).build());
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
