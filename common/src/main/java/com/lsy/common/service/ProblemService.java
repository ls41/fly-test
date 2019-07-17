package com.lsy.common.service;

import com.lsy.common.domain.Problem;
import com.lsy.common.domain.Selection;
import com.lsy.common.repository.ProblemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
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

	public List<Problem> createByString(String s, Long bookId, Long chapterId) {
//		char[] chars = s.toCharArray();
//		char charA = 'A';
//		char charB = 'B';
//		char charC = 'C';
//		char charD = 'D';
//		char dot = '.';
//		char n = '\n';
//		char r = '\r';
//		int index = 0;
//		for (int i = 0; i < chars.length - 1; i++) {
//			if (chars[i] == r && chars[i + 1] == n && i == 0)
//				log.info("start import");
//			if (47 < chars[i] && chars[i] < 58)
//				log.info(String.valueOf(chars[i]));
//		}
//		return null;


		String enter = "\r\n";
		String leftBracket = "（";
		int index = 0;
		int sort = 0;
		StringBuilder stringBuilder = new StringBuilder(s);

		List<Problem> needToSaveProblem = new ArrayList<>();
		List<Selection> needToSaveSelection = new ArrayList<>();
		for (; index < stringBuilder.length(); ) {
			System.err.println(sort);
			Problem problem = Problem.builder().chapterId(chapterId).bookId(bookId).sort(++sort).build();
			int nextEnter = stringBuilder.indexOf(enter, index + 2);
			//要求文本开头没有空行
			problem.setContent(stringBuilder.substring(index, nextEnter - 3));
			Problem savedProblem = this.problemRepository.save(problem);
			needToSaveProblem.add(savedProblem);

			int selectC = stringBuilder.indexOf(enter, stringBuilder.indexOf(enter, nextEnter + 2) + 2) + 2;
			if (selectC >= stringBuilder.length())
				break;
			int selectD = stringBuilder.indexOf(enter, selectC) + 2;
			if (selectD >= stringBuilder.length())
				break;
			if (stringBuilder.charAt(selectC) != 'C') {
				Selection selectionA = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
				Selection selectionB = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
				int indexOfLeftBracket = stringBuilder.indexOf(leftBracket, index);
				switch (stringBuilder.charAt(indexOfLeftBracket + 1)) {
					case 'A': {
						selectionA.setCorrect(true);
						break;
					}
					case 'B': {
						selectionB.setCorrect(true);
						break;
					}
				}
				//计算换行下标
				int nextLine = nextEnter + 2;
				nextEnter = stringBuilder.indexOf(enter, nextLine);
				selectionA.setContent(stringBuilder.substring(nextLine, nextEnter));
				nextLine = nextEnter + 2;

				nextEnter = stringBuilder.indexOf(enter, nextLine);
				selectionB.setContent(stringBuilder.substring(nextLine, nextEnter));

				index = nextEnter + 2;

				needToSaveSelection.add(selectionA);
				needToSaveSelection.add(selectionB);

				continue;
			}

			if (stringBuilder.charAt(selectD) != 'D') {
				Selection selectionA = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
				Selection selectionB = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
				Selection selectionC = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
				int indexOfLeftBracket = stringBuilder.indexOf(leftBracket, index);
				switch (stringBuilder.charAt(indexOfLeftBracket + 1)) {
					case 'A': {
						selectionA.setCorrect(true);
						break;
					}
					case 'B': {
						selectionB.setCorrect(true);
						break;
					}
					case 'C': {
						selectionC.setCorrect(true);
						break;
					}
				}
				//计算换行下标
				int nextLine = nextEnter + 2;
				nextEnter = stringBuilder.indexOf(enter, nextLine);
				selectionA.setContent(stringBuilder.substring(nextLine, nextEnter));
				nextLine = nextEnter + 2;

				nextEnter = stringBuilder.indexOf(enter, nextLine);
				selectionB.setContent(stringBuilder.substring(nextLine, nextEnter));
				nextLine = nextEnter + 2;

				nextEnter = stringBuilder.indexOf(enter, nextLine);
				selectionC.setContent(stringBuilder.substring(nextLine, nextEnter));

				index = nextEnter + 2;

				needToSaveSelection.add(selectionA);
				needToSaveSelection.add(selectionB);
				needToSaveSelection.add(selectionC);

				continue;
			}

			Selection selectionA = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
			Selection selectionB = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
			Selection selectionC = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
			Selection selectionD = Selection.builder().problemId(savedProblem.getId()).correct(false).build();
			int indexOfLeftBracket = stringBuilder.indexOf(leftBracket, index);
			switch (stringBuilder.charAt(indexOfLeftBracket + 1)) {
				case 'A': {
					selectionA.setCorrect(true);
					break;
				}
				case 'B': {
					selectionB.setCorrect(true);
					break;
				}
				case 'C': {
					selectionC.setCorrect(true);
					break;
				}
				case 'D': {
					selectionD.setCorrect(true);
					break;
				}
			}
			//计算换行下标
			int nextLine = nextEnter + 2;
			nextEnter = stringBuilder.indexOf(enter, nextLine);
			selectionA.setContent(stringBuilder.substring(nextLine, nextEnter));
			nextLine = nextEnter + 2;

			nextEnter = stringBuilder.indexOf(enter, nextLine);
			selectionB.setContent(stringBuilder.substring(nextLine, nextEnter));
			nextLine = nextEnter + 2;

			nextEnter = stringBuilder.indexOf(enter, nextLine);
			selectionC.setContent(stringBuilder.substring(nextLine, nextEnter));
			nextLine = nextEnter + 2;

			nextEnter = stringBuilder.indexOf(enter, nextLine);
			selectionD.setContent(stringBuilder.substring(nextLine, nextEnter));

			index = nextEnter + 2;

			needToSaveSelection.add(selectionA);
			needToSaveSelection.add(selectionB);
			needToSaveSelection.add(selectionC);
			needToSaveSelection.add(selectionD);
		}


		this.selectionService.getRepository().saveAll(needToSaveSelection);
		return problemRepository.saveAll(needToSaveProblem);

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
