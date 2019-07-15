package com.lsy.common.repository;

import com.lsy.common.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaSpecificationExecutor<Problem>, JpaRepository<Problem, Long> {


}
