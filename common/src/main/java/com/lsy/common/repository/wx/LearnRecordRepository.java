package com.lsy.common.repository.wx;

import com.lsy.common.domain.wx.LearnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnRecordRepository extends JpaSpecificationExecutor<LearnRecord>, JpaRepository<LearnRecord, Long> {

}
