package com.lsy.common.domain.wx;


import com.lsy.common.domain.AbstractAuditingEntity;

import javax.validation.constraints.NotNull;

public abstract class Record extends AbstractAuditingEntity {
    @NotNull
    private Long ProblemId;

    private String content;

    public Long getProblemId() {
        return ProblemId;
    }

    public void setProblemId(Long problemId) {
        ProblemId = problemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
