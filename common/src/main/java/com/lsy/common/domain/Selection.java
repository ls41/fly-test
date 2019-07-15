package com.lsy.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bz_selection")
@Data
public class Selection extends AbstractAuditingEntity {

//    private static final long serialVersionUID = 1L;

	@NotNull
	private Long problemId;

	@Column(nullable = false)
	private boolean correct = false;

	@NotBlank
	private String content;

}
