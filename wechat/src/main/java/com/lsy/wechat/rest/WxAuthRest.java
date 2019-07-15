package com.lsy.wechat.rest;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.lsy.common.domain.wx.WeChatUser;
import com.lsy.common.service.WeChatUserService;
import com.lsy.wechat.domain.UserToken;
import com.lsy.wechat.service.UserTokenManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 鉴权服务
 */
@RestController
@RequestMapping("/wx/auth")
@Validated
public class WxAuthRest {

	private final WxMaService wxMaService;

	private final WeChatUserService weChatUserService;

	public WxAuthRest(WxMaService wxMaService, WeChatUserService weChatUserService) {
		this.wxMaService = wxMaService;
		this.weChatUserService = weChatUserService;
	}

	@PostMapping("login")
	public Object loginByWeChat(@RequestBody @NotNull WeChatUser weChatUser, HttpServletRequest request) {
		String sessionKey = null;
		String openId = null;
		try {
			WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(weChatUser.getCode());
			sessionKey = result.getSessionKey();
			openId = result.getOpenid();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sessionKey == null || openId == null) {
			throw new RuntimeException();
		}

		Optional<WeChatUser> storageUser=this.weChatUserService.findByOpenId(openId);
		if (!storageUser.isPresent())
			weChatUserService.create(weChatUser);
		else {
			weChatUser.setId(storageUser.get().getId());
			weChatUser=weChatUserService.update(weChatUser);
		}

		// token
		UserToken userToken = UserTokenManager.generateToken(weChatUser.getId());
		userToken.setSessionKey(sessionKey);

		Map<Object, Object> result = new HashMap<>();
		result.put("token", userToken.getToken());
		result.put("tokenExpire", userToken.getExpireTime().toString());
		result.put("weChatUser", weChatUser);
		return request;
	}


	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		UserTokenManager.removeToken(id);
	}


}
