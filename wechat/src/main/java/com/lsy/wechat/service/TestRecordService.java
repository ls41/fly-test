package com.lsy.wechat.service;

import com.lsy.common.domain.AbstractAuditingEntity;
import com.lsy.common.domain.wx.TestRecord;
import com.lsy.common.repository.wx.TestRecordRepository;
import com.lsy.common.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TestRecordService extends AbstractService<TestRecord> {

	private final TestRecordRepository testRecordRepository;

	public TestRecordService(TestRecordRepository testRecordRepository) {
		this.testRecordRepository = testRecordRepository;
	}

	@Override
	public JpaRepository<TestRecord, Long> getRepository() {
		return testRecordRepository;
	}

	@Override
	public JpaSpecificationExecutor<TestRecord> getExecutor() {
		return testRecordRepository;
	}

	public TestRecord createByWXUserId(Long userId) {
		return this.create(TestRecord.builder()
				.weChatUserId(userId)

				.build());
	}

	public TestRecord findLastOne(Long userId) {
		List<TestRecord> testRecords = this.findByExample(TestRecord.builder().weChatUserId(userId).build());
		if (testRecords.size() == 0)
			throw new RuntimeException();
		testRecords.sort(Comparator.comparing(AbstractAuditingEntity::getCreatedDate));
		return testRecords.get(testRecords.size() - 1);
	}
}
