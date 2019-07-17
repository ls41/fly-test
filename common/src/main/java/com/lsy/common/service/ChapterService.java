package com.lsy.common.service;

import com.lsy.common.domain.Chapter;
import com.lsy.common.repository.ChapterRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ChapterService extends AbstractService<Chapter> {

	private final ChapterRepository chapterRepository;

	private final ProblemService problemService;


	public ChapterService(ChapterRepository chapterRepository, ProblemService problemService) {
		this.chapterRepository = chapterRepository;
		this.problemService = problemService;
	}

	public List<Chapter> findByBookId(Long id) {
		List<Chapter> storageList = this.findByExample(Chapter.builder().bookId(id).build());
		storageList.parallelStream().forEach(chapter -> chapter.setProblems(problemService.findByChapterId(chapter.getId())));
		return storageList;
	}


	@Override
	public JpaRepository<Chapter, Long> getRepository() {
		return this.chapterRepository;
	}

	@Override
	public JpaSpecificationExecutor<Chapter> getExecutor() {
		return chapterRepository;
	}

}
