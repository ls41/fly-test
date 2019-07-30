package com.lsy.common.repository.wx;

import com.lsy.common.domain.wx.TestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRecordRepository extends JpaSpecificationExecutor<TestRecord>, JpaRepository<TestRecord, Long> {

}
