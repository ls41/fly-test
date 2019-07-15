package com.lsy.common.service;

import com.lsy.common.domain.Problem;
import com.lsy.common.repository.ProblemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//		return this.getExecutor().findAll((root, query, criteriaBuilder) ->
//				query.where(criteriaBuilder.equal(root.get("chapterId").as(Long.class),id)).getRestriction()
//				);
		return this.findByExample(Problem.builder().chapterId(id).build());
	}



}
