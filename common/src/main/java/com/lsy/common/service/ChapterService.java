package com.lsy.common.service;

import com.lsy.common.domain.Chapter;
import com.lsy.common.repository.ChapterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ChapterService extends AbstractService<Chapter> {

    private final Logger log = LoggerFactory.getLogger(ChapterService.class);

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
