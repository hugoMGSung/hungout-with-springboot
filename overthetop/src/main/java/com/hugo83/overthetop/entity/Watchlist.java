package com.hugo83.overthetop.entity;

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
import jakarta.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "WATCHLIST")
public class Watchlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VIEWNO")
	private Long viewno;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@UpdateTimestamp // 변경시에도 날짜 정보 변경
	private Date viewdate;

	// 프로필
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profileno", referencedColumnName = "profileno")
	private Profile profile;

	// 작품 목록
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videocode", referencedColumnName = "videocode")
	private Videolist videolist;

	// 리뷰
	@ToString.Exclude
	@OneToMany(mappedBy = "watchlist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Review> review;
}
