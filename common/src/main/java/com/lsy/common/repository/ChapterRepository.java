package com.lsy.common.repository;

import com.lsy.common.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaSpecificationExecutor<Chapter>, JpaRepository<Chapter, Long> {

}
