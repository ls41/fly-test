package com.lsy.common.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bz_problem")
public class Problem extends AbstractAuditingEntity {

//    private static final long serialVersionUID = 1L;
    @NotNull
    private Long chapterId;

    @NotNull
    private Integer sort;

    @NotBlank
    private String content;

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
