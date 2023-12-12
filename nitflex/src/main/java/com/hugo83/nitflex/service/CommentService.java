package com.hugo83.nitflex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo83.nitflex.domain.CommentVO;
import com.hugo83.nitflex.mapper.CommentMapper;

import java.util.List;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;
	
	public int insertCommnet(CommentVO comment) {
		return commentMapper.insertCommnet(comment);
	}

	public List<CommentVO> getCommentList(Long movie_idx) {
		return commentMapper.getCommentList(movie_idx);
	}
    
	public int deleteComment(Long idx) {
		return commentMapper.deleteComment(idx);
	}
}
