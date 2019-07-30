package com.lsy.common.repository.wx;

import com.lsy.common.domain.wx.TestDetailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDetailRecordRepository extends JpaSpecificationExecutor<TestDetailRecord>, JpaRepository<TestDetailRecord, Long> {

}
