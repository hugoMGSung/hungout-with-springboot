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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
// import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROFILENO")
	private Long profileno;

	@Column(length = 20)
	private String nickname;

	@Column(length = 125)
	private String profilepw;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp
	private Date regdate;

	private Long reviewban;

	private String keyword;

	// 멤버
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email", referencedColumnName = "email")
	private Member member;

	// ---- 위는 실제 컬럼
	// 구매내역
	@OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Paymentlist> paymentlist;

	// 시청목록
	@ToString.Exclude
	@OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Watchlist> watchlist;

	// 프로필이미지
	@ToString.Exclude
	@OneToOne(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Profileimg profileimg;

	// 문의글
	@OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Board> board = new ArrayList<>();

	// 문의답글
	@OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Qnareply> qnareply = new ArrayList<>();

	// //나중에 볼 영상
	@OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Interestlist> interestlists = new ArrayList<>();
}