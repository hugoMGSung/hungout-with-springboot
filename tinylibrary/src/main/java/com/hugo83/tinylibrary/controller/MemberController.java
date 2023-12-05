package com.hugo83.tinylibrary.controller;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hugo83.tinylibrary.dto.MemberJoinDTO;
import com.hugo83.tinylibrary.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/login")
	public void getLogin(String error, String logout) {
		log.info("login get..............");
		log.info("logout: " + logout);

		if (logout != null) {
			log.info("user logout............");
		}
	}

	@GetMapping(value = "/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/member/login";
	}

	@GetMapping(value = "/join")
	public void getJoin() {
		log.info("join get...");
	}

	@PostMapping(value = "/join")
	public String postJoin(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
		log.info("JOIN POST ............... ");
		memberJoinDTO.setJoinDate(LocalDate.now());

		if (memberService.checkDuplicateEmail(memberJoinDTO.getEmail())) {
			redirectAttributes.addFlashAttribute("error", "emaildup");
			redirectAttributes.addFlashAttribute("errmsg", "동일한 이메일이 존재합니다.");
			return "redirect:/member/join";
		}
		
		try {
			memberService.join(memberJoinDTO);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "joinfail");
			redirectAttributes.addFlashAttribute("errmsg", "회원가입 실패! 관리자에게 문의하세요");
			return "redirect:/member/join";
		}

		redirectAttributes.addFlashAttribute("result", "success");
		redirectAttributes.addFlashAttribute("message", "회원가입에 성공했습니다.");
		return "redirect:/member/login";
	}
}
