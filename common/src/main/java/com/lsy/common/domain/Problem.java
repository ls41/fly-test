package com.lsy.common.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bz_problem")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem extends AbstractAuditingEntity {

//    private static final long serialVersionUID = 1L;
    @NotNull
    private Long chapterId;

    @NotNull
    private Integer sort;

    @NotBlank
    private String content;

    @Transient
    private List<Selection> selections;
}
