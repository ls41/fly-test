package com.lsy.common.domain.wx;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsy.common.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wx_error_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRecord extends AbstractAuditingEntity {
	@NotNull
	private Long problemId;

	@NotNull
	private Long weChatUserId;

	@NotNull
	private Long selectionId;

	private String content;

	public Integer getNum() {
		return num;
	}

	@JsonIgnore
	public void setNum(Integer num) {
		this.num = num;
	}

	private Integer num;

}
