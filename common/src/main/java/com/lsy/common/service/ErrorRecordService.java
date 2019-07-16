package com.lsy.common.service;

import com.lsy.common.domain.wx.ErrorRecord;
import com.lsy.common.repository.ErrorRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public JpaRepository<ErrorRecord, Long> getRepository() {
		return this.errorRecordRepository;
	}

	@Override
	public JpaSpecificationExecutor<ErrorRecord> getExecutor() {
		return errorRecordRepository;
	}

}
