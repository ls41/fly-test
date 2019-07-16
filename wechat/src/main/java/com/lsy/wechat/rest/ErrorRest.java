package com.lsy.wechat.rest;

import com.lsy.common.service.ErrorRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/error")
@Slf4j
public class ErrorRest {

	private final ErrorRecordService errorRecordService;

	public ErrorRest(ErrorRecordService errorRecordService) {
		this.errorRecordService = errorRecordService;
	}

}
