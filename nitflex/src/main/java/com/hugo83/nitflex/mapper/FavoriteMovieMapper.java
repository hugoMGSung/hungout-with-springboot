package com.hugo83.nitflex.mapper;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Param;
// import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hugo83.nitflex.domain.FavoriteMovieVO;

// import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;

@Mapper
@Repository
public interface FavoriteMovieMapper {
	List<FavoriteMovieVO> fetchMovie(Long user_idx);

	// List<NetflixCloneVO> fetchMovie(); //즐겨찾기 모두 가져오기
	void addMovie(FavoriteMovieVO nitflex); // 즐겨찾기 추가

	void removeMovieByIdx(Long idx); // 즐겨찾기 삭제

	void removeMovieByMovieId(Long movie_id); // 내가찜한컨텐츠목록말고 다른화면에서 삭제하기

	int isMovieByMovieAndUser(Long movie_id, Long user_idx); // 찜 목록에 있는지 확인

	int isMovieByMovie(Long movie_id);
}
