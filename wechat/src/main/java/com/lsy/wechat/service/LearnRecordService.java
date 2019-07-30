package com.lsy.wechat.service;

import com.lsy.common.domain.wx.LearnRecord;
import com.lsy.common.repository.ProblemRepository;
import com.lsy.common.repository.wx.LearnRecordRepository;
import com.lsy.common.service.AbstractService;
import com.lsy.wechat.domain.dto.LastVisitDto;
import org.bouncycastle.crypto.DataLengthException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public class LearnRecordService extends AbstractService<LearnRecord> {

	private final LearnRecordRepository learnRecordRepository;

	private final ProblemRepository problemRepository;

	public LearnRecordService(LearnRecordRepository learnRecordRepository, ProblemRepository problemRepository) {
		this.learnRecordRepository = learnRecordRepository;
		this.problemRepository = problemRepository;
	}

	public LastVisitDto getVisitData(Long userId) {
		List<LearnRecord> records = this.findByExample(LearnRecord.builder().weChatUserId(userId).build());
		LastVisitDto dto = new LastVisitDto();
		dto.setTotal(problemRepository.count());
		if (records.size() == 1) {
			dto.setComplete(problemRepository.count((root, query, criteriaBuilder) -> query.where(
					criteriaBuilder.lessThan(root.get("id").as(Long.class), records.get(0).getLastVisitProblemId())
			).getRestriction()));
		} else {
			dto.setComplete(0L);
		}
		return dto;
	}

	public void createLastVisitData(Long userId, Long problemId) {
		List<LearnRecord> records = this.findByExample(LearnRecord.builder().weChatUserId(userId).build());
		if (records.size() == 0) {
			this.create(LearnRecord.builder()
					.weChatUserId(userId)
					.lastVisitProblemId(problemId)
					.build()
			);
		} else if (records.size() == 1) {
			records.get(0).setLastVisitProblemId(problemId);
			this.update(records.get(0));
		} else
			throw new DataLengthException();
	}

	@Override
	public JpaRepository<LearnRecord, Long> getRepository() {
		return this.learnRecordRepository;
	}

	@Override
	public JpaSpecificationExecutor<LearnRecord> getExecutor() {
		return this.learnRecordRepository;
	}

}
