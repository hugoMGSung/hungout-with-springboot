package com.hugo83.nitflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hugo83.nitflex.domain.CommentVO;
import com.hugo83.nitflex.domain.UserVO;
import com.hugo83.nitflex.service.CommentService;
import com.hugo83.nitflex.service.UserService;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Log4j2
public class CommentController {
	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@PostMapping(value = "/write")
	int writeMovieComment(@RequestBody CommentVO comment) {
		log.info(comment);
		//해당 코멘트의 id값으로 user정보 불러옴
		UserVO userInfo = userService.getUserByIdx(comment.getUser_idx());
		log.info(userInfo.toString());
		log.info(userInfo.toString());
		log.info(userInfo.toString());
		log.info(userInfo.toString());
		
		comment.setUser_idx(userInfo.getIdx());
		int cnt = commentService.insertCommnet(comment);
		//실행결과값
		return cnt;
	}

	@GetMapping(value = "/list")
	List<CommentVO> commentList(@RequestParam(value = "movie_idx", defaultValue = "0") Long movie_idx) {
		List<CommentVO> list = commentService.getCommentList(movie_idx);

		return list;
	}
	
	@GetMapping(value = "/delete")
	String deleteComment(@RequestParam(value = "idx") Long idx) {
		log.info(idx); //해당 댓글 인덱스값
		commentService.deleteComment(idx);

		return "삭제성공";
	}
}
