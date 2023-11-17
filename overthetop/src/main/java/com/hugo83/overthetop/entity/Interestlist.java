package com.hugo83.overthetop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "INTERESTLIST")
public class Interestlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interestno;

	// private Long videocode;

	// private Long profileno;

	// 작품 테이블 연결
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videocode", referencedColumnName = "videocode")
	private Videolist videolist;

	// 프로필 테이블 연결
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profileno", referencedColumnName = "profileno")
	private Profile profile;
}