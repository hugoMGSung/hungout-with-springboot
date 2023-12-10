package com.hugo83.toybatis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {
	private String resultCode;
	private Object res;
}