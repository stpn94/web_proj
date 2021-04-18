package web_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import web_proj.dao.impl.BoardDaoImpl;
import web_proj.ds.JndiDs;
import web_proj.dto.BoardBeanDto;

public class BoardDetailService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();
	
	public BoardDetailService() {
		dao.setCon(con);
	}

	public BoardBeanDto getArticle(int board_num) {
		//조회수 증가
		dao.updateReadCount(board_num);
		//board_num에 해당하는 BoardDto(게시글 내용들) return;
		return dao.selectArticle(board_num);
				
	}
}