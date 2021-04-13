package web_proj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web_proj.dao.BoardDao;
import web_proj.dto.BoardBeanDto;

public class BoardDaoImpl implements BoardDao {
	private static final BoardDaoImpl instance = new BoardDaoImpl();
	private Connection con;

	private BoardDaoImpl() {
	}

	public static BoardDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int selectListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<BoardBeanDto> selectArticleList(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardBeanDto selectArticle(int board_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReplyArticle(BoardBeanDto article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateArticel(BoardBeanDto article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArticle(int board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReadCount(int board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertArticle(BoardBeanDto article) {
		String sql = "INSERT INTO web_gradle_erp.board" 
				   + " (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)"
				   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());";
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, nextBoardNum());
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, article.getBoard_re_ref());
			pstmt.setInt(8, article.getBoard_re_lev());
			pstmt.setInt(9, article.getBoard_re_seq());
			pstmt.setInt(10, article.getBoard_readcount());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	@Override
	public int nextBoardNum() {
		String sql = "select max(BOARD_NUM) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (
		SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
