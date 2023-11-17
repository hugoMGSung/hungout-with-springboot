package com.hugo83.overthetop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "Videolist")
public class Videolist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VIDEOCODE")
	private Long videocode;

	private String title;

	private String keyword;

	private String pd;

	private String chkage;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp
	private Date regdate;

	private String opendate;

	private Long price;

	private Long episode;

	@Lob
	private String linkurl;

	// 임시변수
	@Transient
	private Long imgno;
	// 연결

	// 출연진
	@OneToMany(mappedBy = "casts_to_videocode", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Casts> castList = new ArrayList<>();

	// 시청목록
	@OneToMany(mappedBy = "videolist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Watchlist> watchlists = new ArrayList<>();

	// 작품이미지
	@OneToMany(mappedBy = "videolist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Videoimg> videoimgs = new ArrayList<>();

	// 구매내역
	@OneToMany(mappedBy = "videolist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Paymentlist> paymentlists = new ArrayList<>();

	// 나중에 볼영상
	@OneToMany(mappedBy = "videolist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Interestlist> interestlists = new ArrayList<>();
}