package com.lsy.wechat.domain.dto;

import com.lsy.common.domain.Selection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestSubmitDto {
	private Long testRecordId;
	private List<Selection> selections;
	private int timeCost;
}
