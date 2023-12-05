package com.hugo83.tinylibrary.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hugo83.tinylibrary.dto.MemberJoinDTO;
import com.hugo83.tinylibrary.entity.Member;
import com.hugo83.tinylibrary.entity.MemberRole;
import com.hugo83.tinylibrary.repository.MemberRepository;
// import com.hugo83.tinylibrary.service.MemberService.MidExistException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final ModelMapper modelMapper;

	private final MemberRepository memberRepository;

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
		String email = memberJoinDTO.getEmail();
		log.info(email);

		// 이메일로의 접근은 프론트엔드에서 RestAPI로 할 것

		Member member = modelMapper.map(memberJoinDTO, Member.class);
		member.changePassword(passwordEncoder().encode(memberJoinDTO.getPassword()));
		member.addRole(MemberRole.USER); // 권한 추가

		memberRepository.save(member);
	}

	@Override
	public boolean checkDuplicateEmail(String email) {
		Optional<Member> result = memberRepository.getEmailWithRoles(email);
		
		if (result.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Member getMemberFromEail(String email) {
		Optional<Member> result = memberRepository.getEmailWithRoles(email);

		if (result.isEmpty()) {
			return null;
		} else {
			Member member = result.get();
			return member;
		}
	}
}
