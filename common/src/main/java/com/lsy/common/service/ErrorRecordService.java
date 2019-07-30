package com.lsy.common.service;

import com.lsy.common.domain.wx.ErrorRecord;
import com.lsy.common.repository.wx.ErrorRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing usrs.
 */
@Service
@Transactional
@Slf4j
public class ErrorRecordService extends AbstractService<ErrorRecord> {

	private final ErrorRecordRepository errorRecordRepository;


	public ErrorRecordService(ErrorRecordRepository errorRecordRepository) {
		this.errorRecordRepository = errorRecordRepository;
	}

	public ErrorRecord saveOrUpdate(ErrorRecord record) {
		List<ErrorRecord> storage = this.findByExample(ErrorRecord.builder().weChatUserId(record.getWeChatUserId()).problemId(record.getProblemId()).build());
		if (storage.size() == 0) {
			record.setNum(1);
			return errorRecordRepository.save(record);
		}
		if (storage.size() == 1) {
			storage.get(0).setNum(storage.get(0).getNum() + 1);
			storage.get(0).setSelectionId(record.getSelectionId());
			storage.get(0).setContent(record.getContent());
			return errorRecordRepository.save(storage.get(0));
		}
		throw new RuntimeException();
	}

	public List<ErrorRecord> saveOrUpdate(List<ErrorRecord> records) {
		List<ErrorRecord> toSave = new ArrayList<>();
		records.parallelStream().forEach(record -> {
			List<ErrorRecord> storage = this.findByExample(ErrorRecord.builder().weChatUserId(record.getWeChatUserId()).problemId(record.getProblemId()).build());
			if (storage.size() == 0) {
				record.setNum(1);
				toSave.add(record);
			}
			if (storage.size() == 1) {
				record.setNum(storage.get(0).getNum() + 1);
				errorRecordRepository.deleteById(storage.get(0).getId());
				toSave.add(record);
			}
			if (storage.size() > 1)
				throw new RuntimeException();
		});
		return this.errorRecordRepository.saveAll(toSave);

	}

	@Override
	public JpaRepository<ErrorRecord, Long> getRepository() {
		return this.errorRecordRepository;
	}

	@Override
	public JpaSpecificationExecutor<ErrorRecord> getExecutor() {
		return errorRecordRepository;
	}

}
