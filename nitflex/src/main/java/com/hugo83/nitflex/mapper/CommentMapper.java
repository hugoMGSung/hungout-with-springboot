package com.hugo83.nitflex.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hugo83.nitflex.domain.CommentVO;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

	@Insert({"INSERT INTO nf_comment (user_idx, movie_idx, contents, reg_date, mod_date) " +
			 "VALUES (#{comment.user_idx},#{comment.movie_idx}, #{comment.contents}, now(), now())"})
    int insertCommnet(@Param("comment") CommentVO comment);

    @Select({"SELECT * FROM nf_comment WHERE movie_idx=#{movie_idx}"})
    List<CommentVO> getCommentList(@Param("movie_idx") Long movie_idx);
    
    @Delete({"DELETE FROM nf_comment where idx=#{idx}"})
    int deleteComment(@Param("idx") Long idx);
}