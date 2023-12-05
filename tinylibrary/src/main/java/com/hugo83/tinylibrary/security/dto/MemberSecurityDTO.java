package com.hugo83.tinylibrary.security.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {
	private String email;
	private String mName;
	private String password;

	public MemberSecurityDTO(String email, String mName, String password, Collection<? extends GrantedAuthority> authorities) {
		super(email, password, authorities);

		this.email = email;
		this.mName = mName;
		this.password = password;
	}
}

