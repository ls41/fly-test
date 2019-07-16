package com.lsy.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class EntityAuditorAware implements AuditorAware<Long> {

	//TODO 在需要自动写入审计信息（创建人，修改人，创建时间，修改时间）的时候，需要在对应的启动类上加上{
	// @EnableJpaAuditing
	// }注解


	@Override
	public Optional<Long> getCurrentAuditor() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx == null) {
			return Optional.empty();
		}
		if (ctx.getAuthentication() == null) {
			return Optional.empty();
		}
		if (ctx.getAuthentication().getPrincipal() == null) {
			return Optional.empty();
		}
		Object principal = ctx.getAuthentication().getPrincipal();
		if (principal.getClass().isAssignableFrom(Long.class)) {
			return Optional.of((long) principal);
		} else {
			return Optional.empty();
		}
	}
}
