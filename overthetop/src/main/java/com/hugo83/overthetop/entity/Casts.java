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
@Table(name = "CASTS")
public class Casts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cast_no;

	// 작품
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videocode", referencedColumnName = "videocode")
	private Videolist casts_to_videocode;

	// 배우
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actors_no", referencedColumnName = "actors_no")
	private Actors actors;
}