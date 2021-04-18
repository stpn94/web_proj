package web_proj.service;

import java.sql.Connection;

import web_proj.dao.impl.BoardDaoImpl;
import web_proj.ds.JndiDs;
import web_proj.dto.BoardBeanDto;

public class BoardWriteService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();
	
	public BoardWriteService() {
		dao.setCon(con);
	}

	public boolean registerArticle(BoardBeanDto boardDTO) {
		return dao.insertArticle(boardDTO)==1 ? true : false;
	}
}
