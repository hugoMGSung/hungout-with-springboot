package com.hugo83.overthetop.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Videoimg")
public class Videoimg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NO")
	private Long no;

	private String filename;

	@Lob
	@ToString.Exclude
	private byte[] filedata;

	private String filetype;

	private Long filesize;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@UpdateTimestamp
	private Date regdate;

	// 작품 목록
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videocode", referencedColumnName = "videocode")
	private Videolist videolist;
}
