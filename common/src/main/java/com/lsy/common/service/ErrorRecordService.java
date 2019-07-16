package com.lsy.common.service;

import com.lsy.common.domain.wx.ErrorRecord;
import com.lsy.common.repository.ErrorRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		if (record.getId() == null)
			return this.errorRecordRepository.save(record);
		Optional<ErrorRecord> storage = errorRecordRepository.findById(record.getId());
		if (storage.isPresent()) {
			record.setNum(storage.get().getNum() + 1);
			record.setProblemId(storage.get().getProblemId());
			this.errorRecordRepository.deleteById(record.getId());
			return this.errorRecordRepository.save(record);
		}
		throw new RuntimeException();
	}

	public List<ErrorRecord> saveOrUpdate(List<ErrorRecord> records) {
		List<ErrorRecord> toSave = new ArrayList<>();
		records.parallelStream().forEach(record -> {
			if (record.getId() == null) {
				toSave.add(record);
			} else {
				Optional<ErrorRecord> storage = errorRecordRepository.findById(record.getId());
				if (storage.isPresent()) {
					record.setNum(storage.get().getNum() + 1);
					record.setProblemId(storage.get().getProblemId());
					this.errorRecordRepository.deleteById(record.getId());
					toSave.add(record);
				}
				throw new RuntimeException();
			}
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
