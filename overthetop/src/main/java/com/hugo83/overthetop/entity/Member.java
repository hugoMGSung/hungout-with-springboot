package com.hugo83.overthetop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

	@Id
	@Column(length = 100)
	private String email;

	@Column(length = 125)
	private String pw;

	@Column(length = 50)
	private String name;

	@Column(length = 20)
	private String phone;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	private Long token;

	@Column(length = 20)
	private String role;

	private boolean social;

	private Long membershipchk;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp
	private Date regdate;

	// 프로필
	@ToString.Exclude
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Profile> profile = new ArrayList<>();

	// 결제 내역
	@ToString.Exclude
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Paychk> paychk = new ArrayList<>();
}