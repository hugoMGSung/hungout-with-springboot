package com.hugo83.overthetop.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Paymentlist")
public class Paymentlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENTLISTNO")
	private Long paymentlistno;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@UpdateTimestamp
	private Date regdate;

	// 작품
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videocode", referencedColumnName = "videocode")
	private Videolist videolist;

	// 프로필
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profileno", referencedColumnName = "profileno")
	private Profile profile;
}
