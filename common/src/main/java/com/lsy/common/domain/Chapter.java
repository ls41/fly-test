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
@Table(name = "bz_chapter")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chapter extends AbstractAuditingEntity {

//    private static final long serialVersionUID = 1L;

    @NotNull
    private Long bookId;

    @NotBlank
    private String name;

    private String intro;

    @NotNull
    private Integer sort;

    @Transient
    private List<Problem> problems;

}
