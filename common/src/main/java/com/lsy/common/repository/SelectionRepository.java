package com.lsy.common.repository;

import com.lsy.common.domain.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionRepository extends JpaSpecificationExecutor<Selection>, JpaRepository<Selection, Long> {


}
