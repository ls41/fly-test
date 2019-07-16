package com.lsy.common.domain.wx;


import com.lsy.common.domain.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wx_error_record")
@Data
@Builder
public class ErrorRecord extends AbstractAuditingEntity {
	@NotNull
	private Long problemId;

	@NotNull
	private Long weChatUserId;

	@NotNull
	private Long selectionId;

	private String content;

	private Integer num;

}
