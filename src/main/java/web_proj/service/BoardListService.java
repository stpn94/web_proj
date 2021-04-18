package web_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import web_proj.dao.impl.BoardDaoImpl;
import web_proj.ds.JndiDs;
import web_proj.dto.BoardBeanDto;

public class BoardListService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();
	
	public BoardListService() {
		dao.setCon(con);
	}

	
	public int getListCount() {
		return dao.selectListCount();
	}
	
	public ArrayList<BoardBeanDto> getArticleList(int page, int limit){
		return dao.selectArticleList(page, limit);
		
	}
}