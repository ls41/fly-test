package com.lsy.common.service;

import com.lsy.common.domain.wx.WeChatUser;
import com.lsy.common.repository.WeChatUserRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WeChatUserService extends AbstractService<WeChatUser> {

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
