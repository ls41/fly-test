package com.lsy.common.service;

import com.lsy.common.domain.wx.WeChatUser;
import com.lsy.common.repository.WeChatUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class for managing usrs.
 */
@Service
@Transactional
public class WeChatUserService extends AbstractService<WeChatUser> {

	private final Logger log = LoggerFactory.getLogger(WeChatUserService.class);

	private final WeChatUserRepository weChatUserRepository;


	public WeChatUserService(WeChatUserRepository weChatUserRepository) {
		this.weChatUserRepository = weChatUserRepository;
	}

	public Optional<WeChatUser> findByOpenId(String openId) {
		WeChatUser user = new WeChatUser();
		user.setOpenId(openId);
		return this.weChatUserRepository.findOne(Example.of(user));
	}


	@Override
	public JpaRepository<WeChatUser, Long> getRepository() {
		return this.weChatUserRepository;
	}

	@Override
	public JpaSpecificationExecutor<WeChatUser> getExecutor() {
		return weChatUserRepository;
	}

}
