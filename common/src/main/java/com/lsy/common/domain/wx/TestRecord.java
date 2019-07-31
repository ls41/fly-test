package com.lsy.common.domain.wx;

import com.lsy.common.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wx_test_record")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestRecord extends AbstractAuditingEntity {
	@NotNull
	private Long weChatUserId;

	private Short sort;

	private Short score;

	private int timeCost;
}
