package com.hugo83.nitflex.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private Long idx;
	private String email;
	private String password;
	private String name;
	private LocalDate red_date;
	private LocalDate mod_date;
}
