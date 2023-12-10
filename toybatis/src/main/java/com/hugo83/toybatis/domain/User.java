package com.hugo83.toybatis.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long idx;

	@NotBlank
    @Email
	private String email;
	private String password;
	private LocalDate reg_date; // reg_date에 대한 매핑!! 
	private LocalDate mod_date;

	// 나중을 위해서
	// @NotBlank
    // @Size(min = 3, max = 100)
    // private String name;
}
