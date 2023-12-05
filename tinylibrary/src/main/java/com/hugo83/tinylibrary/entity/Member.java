package com.hugo83.tinylibrary.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member {
	@Id
	@Column(length = 120)
	private String email;

	@Column(length = 100, nullable = false)
	private String mName;

	private String password;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate joinDate;

	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private Set<MemberRole> roleSet = new HashSet<>();

	public void changePassword(String password) {
		this.password = password;
	}

	public void addRole(MemberRole MemberRole) {
		this.roleSet.add(MemberRole);
	}

	public void clearRoles() {
		this.roleSet.clear();
	}
}
