package com.lsy.common.repository.wx;

import com.lsy.common.domain.wx.WeChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WeChatUserRepository extends JpaSpecificationExecutor<WeChatUser>, JpaRepository<WeChatUser, Long> {
}
