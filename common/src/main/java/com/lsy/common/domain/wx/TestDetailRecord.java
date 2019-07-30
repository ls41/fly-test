package com.lsy.common.domain.wx;

import com.lsy.common.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wx_test_detail_record")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDetailRecord extends AbstractAuditingEntity {


	private Long testRecordId;

	private Long selectionId;

	private Long problemId;
}
