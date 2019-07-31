package com.lsy.wechat.rest;

import com.lsy.wechat.config.LoginUser;
import com.lsy.wechat.domain.dto.HomeDto;
import com.lsy.wechat.service.LearnRecordService;
import com.lsy.wechat.service.TestRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/wx/home")
@RestController
public class HomeRest {
	private final TestRecordService testRecordService;
	private final LearnRecordService learnRecordService;

	public HomeRest(TestRecordService testRecordService, LearnRecordService learnRecordService) {
		this.testRecordService = testRecordService;
		this.learnRecordService = learnRecordService;
	}


	@GetMapping("/myHomeData")
	public HomeDto getMyHomeData(@LoginUser Long userId) {
		return HomeDto.builder()
				.lastTestRecord(this.testRecordService.getLastedOne(userId))
				.overTime(LocalDateTime.now().plusDays(1L))
				.lastVisitDto(learnRecordService.getVisitData(userId))
				.build();
	}

}
