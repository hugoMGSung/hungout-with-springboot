package com.hugo83.overthetop.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "ACTORS")
public class Actors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long actors_no;

	private String actors_name;

	// 출연진
	@ToString.Exclude
	@OneToMany(mappedBy = "actors", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Casts> casts = new ArrayList<>();
}
