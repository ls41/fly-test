package com.lsy.wechat.domain.dto;

import com.lsy.common.domain.wx.TestRecord;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HomeDto {
	private LocalDateTime overTime;
	private LastVisitDto lastVisitDto;
	private TestRecord lastTestRecord;
}
