package web_proj.service;

import java.sql.Connection;

import web_proj.dao.impl.BoardDaoImpl;
import web_proj.ds.JndiDs;
import web_proj.dto.BoardBeanDto;

public class BoardDeleteService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();
	
	public BoardDeleteService() {
		dao.setCon(con);
	}
	
	public boolean isArticleWriter(int board_num,String pass) {
		return dao.isArticleBoardWriter(board_num,pass);
	}
	
	public boolean removeArticle(int board_num) {
		return dao.deleteArticle(board_num)==1 ? true : false;
	}
}
