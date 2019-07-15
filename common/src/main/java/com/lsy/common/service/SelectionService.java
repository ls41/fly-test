package com.lsy.common.service;

import com.lsy.common.domain.Selection;
import com.lsy.common.repository.SelectionRepository;
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
public class SelectionService extends AbstractService<Selection> {

    private final Logger log = LoggerFactory.getLogger(SelectionService.class);

    private final SelectionRepository selectionRepository;


    public SelectionService(SelectionRepository selectionRepository) {
        this.selectionRepository = selectionRepository;
    }


    @Override
    public JpaRepository<Selection, Long> getRepository() {
        return this.selectionRepository;
    }

    @Override
    public JpaSpecificationExecutor<Selection> getExecutor() {
        return selectionRepository;
    }

}
