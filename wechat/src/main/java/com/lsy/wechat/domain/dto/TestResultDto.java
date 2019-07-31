package com.lsy.wechat.domain.dto;

import com.lsy.common.domain.Selection;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TestResultDto {
	private Short score;
	private Short error;
	private List<Selection> selections = new ArrayList<>();
}
