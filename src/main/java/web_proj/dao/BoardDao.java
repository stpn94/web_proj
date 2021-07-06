package web_proj.dao;

import java.util.ArrayList;

import web_proj.dto.BoardBeanDto;

public interface BoardDao {

	int selectListCount();

	ArrayList<BoardBeanDto> selectArticleList(int page, int limit);

	BoardBeanDto selectArticle(int board_num);

	int insertArticle(BoardBeanDto article);

	int insertReplyArticle(BoardBeanDto article);

	int updateArticel(BoardBeanDto article);

	int deleteArticle(int board_num);

	int updateReadCount(int board_num);
	
	int nextBoardNum();
}
