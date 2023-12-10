package com.hugo83.toybatis.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long idx;
	private String email;
	private String password;
	private LocalDate reg_date; // reg_date에 대한 매핑!! 
	private LocalDate mod_date;
}
