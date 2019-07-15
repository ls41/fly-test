package com.lsy.common.domain.wx;

import com.lsy.common.domain.AbstractAuditingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wx_user")
@Data
public class WeChatUser extends AbstractAuditingEntity {
	private String code;
	private String nickName;
	private String avatarUrl;
	private String country;
	private String province;
	private String city;
	private String language;
	private String openId;
	private Byte gender;
}
