package com.lsy.wechat.domain.dto;

import com.lsy.common.domain.Problem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestDto {
	private Long testRecordId;
	private List<Problem> problems;
	private int timeCost = 60 * 60;
}
