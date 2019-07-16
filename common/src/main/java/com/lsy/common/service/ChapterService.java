package com.lsy.common.service;

import com.lsy.common.domain.Chapter;
import com.lsy.common.repository.ChapterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
@Slf4j
public class ChapterService extends AbstractService<Chapter> {

    private final ChapterRepository chapterRepository;


    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }


    @Override
    public JpaRepository<Chapter, Long> getRepository() {
        return this.chapterRepository;
    }

    @Override
    public JpaSpecificationExecutor<Chapter> getExecutor() {
        return chapterRepository;
    }

}
