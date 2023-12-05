package com.hugo83.tinylibrary.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

/**
 * MemberJoinDTO
 */
@Data
public class MemberJoinDTO {
	@NonNull
	@NotBlank(message = "아이디용 이메일은 필수입니다")
	private String email;

	@NonNull
	@NotBlank(message = "회원명은 필수입니다")
	private String mName;

	@NonNull
	@NotBlank(message = "패스워드는 필수입니다")
	private String password;

	private LocalDate joinDate; 
}