package com.hugo83.tinylibrary.service;

import com.hugo83.tinylibrary.dto.MemberJoinDTO;
import com.hugo83.tinylibrary.entity.Member;

public interface MemberService {
	static class MidExistException extends Exception {

	}

	void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

	boolean checkDuplicateEmail(String email);

	Member getMemberFromEail(String email);
}
