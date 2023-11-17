package com.hugo83.overthetop.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString;

//멤버십 요금제
@Data
@Entity
@Table(name = "FEE")
public class Fee {

	@Id
	private Long grade;

	private Long price;

	private String name;

	private Long capacity;

	private String expiration;

	// 결제 내역
	@ToString.Exclude
	@OneToMany(mappedBy = "fee", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Paychk> paychk;
}