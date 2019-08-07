package com.lsy.wechat.config;

import com.lsy.wechat.service.UserTokenManager;
import org.apache.http.client.HttpResponseException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	public static final String LOGIN_TOKEN_KEY = "we-chat-token";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(Long.class) && parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
								  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

		String token = request.getHeader(LOGIN_TOKEN_KEY);
		if (token == null || token.isEmpty()) {
			throw new HttpResponseException(500, "TOKEN为空");
		}
		Long rtn = UserTokenManager.getUserId(token);
		rtn = rtn == null ? UserTokenManager.testGetUserId(token) : rtn;
		if (rtn == null)
			throw new HttpResponseException(500, "TOKEN无效");
//		return UserTokenManager.getUserId(token);

		return rtn;
	}
}
