package com.hugo83.overthetop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "REVIEWLIKES")
public class Reviewlikes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewlikes_no;

	private Long profileno;

	@ColumnDefault("0")
	private Long chklikes = Long.valueOf(0);

	// private Long review_no;

	// 리뷰
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_no", referencedColumnName = "review_no")
	private Review review;
}