package com.lsy.wechat.rest;

import com.lsy.common.domain.wx.ErrorRecord;
import com.lsy.common.service.ErrorRecordService;
import com.lsy.wechat.config.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("wx/error")
@Slf4j
public class ErrorRest {

	private final ErrorRecordService errorRecordService;

	public ErrorRest(ErrorRecordService errorRecordService) {
		this.errorRecordService = errorRecordService;
	}

	@PostMapping("newOne")
	public ErrorRecord save(@LoginUser Long userId, @RequestBody @NotNull ErrorRecord record) {
		record.setWeChatUserId(userId);
		return errorRecordService.saveOrUpdate(record);
	}

	@PostMapping("newList")
	public List<ErrorRecord> saveList(@LoginUser Long userId, @RequestBody @NotNull List<ErrorRecord> records) {
		records.forEach(record -> record.setWeChatUserId(userId));
		return errorRecordService.saveOrUpdate(records);
	}

	@GetMapping("myList")
	public List<ErrorRecord> getMyList(@LoginUser Long userId) {
		return errorRecordService.findByExample(ErrorRecord.builder().weChatUserId(userId).build());
	}

	@DeleteMapping
	public void delete(@LoginUser Long userId, @RequestParam @NotNull Long problemId) {
		List<ErrorRecord> records = errorRecordService.findByExample(ErrorRecord.builder().weChatUserId(userId).problemId(problemId).build());
		if (records.size() != 1)
			throw new RuntimeException();
		errorRecordService.deleteById(records.get(0).getId());
	}
}
