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

@Data
@Entity
@Table(name = "CHARGETOKEN")
public class Chargetoken {

	@Id
	private String token;

	private Long price;

	private Long quantity;

	// 결제내역
	@ToString.Exclude
	@OneToMany(mappedBy = "chargetoken", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Paychk> paychk;
}
