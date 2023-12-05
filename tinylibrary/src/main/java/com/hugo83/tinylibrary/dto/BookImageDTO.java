package com.hugo83.tinylibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookImageDTO {
	private String uuid;
	private String fileName;
	private int ord;
}
