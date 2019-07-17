package com.lsy.common.service;

import com.lsy.common.domain.Selection;
import com.lsy.common.repository.SelectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing users.
 */
@Service
@Transactional
@Slf4j
public class SelectionService extends AbstractService<Selection> {

    private final SelectionRepository selectionRepository;


    public SelectionService(SelectionRepository selectionRepository) {
        this.selectionRepository = selectionRepository;
    }

    public List<Selection> findByProblemId(Long id) {
        return this.findByExample(Selection.builder().problemId(id).build());
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
