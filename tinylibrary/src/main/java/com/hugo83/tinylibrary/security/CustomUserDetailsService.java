package com.hugo83.tinylibrary.security;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hugo83.tinylibrary.entity.Member;
import com.hugo83.tinylibrary.repository.MemberRepository;
import com.hugo83.tinylibrary.security.dto.MemberSecurityDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;

	//private PasswordEncoder passwordEncoder;

	public CustomUserDetailsService() {
		new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("LOADUSERBYUSERNAME ::::: " + username); // email임을 주의!!!
		Optional<Member> result = memberRepository.getEmailWithRoles(username);

		// // UserDetails userDetails = User.builder().username("user1")
		// // 	.password(passwordEncoder.encode("1111"))
		// // 	.authorities("ROLE_USER")
		// // 		.build();

		if (result.isEmpty()) {
			throw new UsernameNotFoundException("사용자 이메일을 찾을 수 없습니다!");
		}

		Member member = result.get();

		MemberSecurityDTO memSecDTO = new MemberSecurityDTO(member.getEmail(), member.getMName(), member.getPassword(),
				member.getRoleSet().stream().map(
						memberRole -> 
							new SimpleGrantedAuthority("ROLE_" + memberRole.name())).collect(Collectors.toList()));

		log.info("MemberSecurityDTO :::::::::::: " + memSecDTO);

		return memSecDTO;
	}
}
