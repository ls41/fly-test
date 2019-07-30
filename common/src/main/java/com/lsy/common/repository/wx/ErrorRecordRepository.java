package com.lsy.common.repository.wx;

import com.lsy.common.domain.wx.ErrorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRecordRepository extends JpaSpecificationExecutor<ErrorRecord>, JpaRepository<ErrorRecord, Long> {

}
