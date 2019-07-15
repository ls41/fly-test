package com.lsy.common.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bz_book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends AbstractAuditingEntity {
	//    private static final long serialVersionUID = 1L;
	@NotBlank
	private String intro;

	@NotBlank
	private String name;

	@Min(value = 0)
	private Integer price;

	@Transient
	private List<Chapter> chapters;

}
