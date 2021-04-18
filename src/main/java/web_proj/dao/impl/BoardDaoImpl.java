package web_proj.dao.impl;

import java.sql.Connection;
import java.sql.Date;
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
		String sql = "select count(*) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				// 레코드 수 리턴
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 그렇지않으면 0이 리턴
		return 0;
	}

	@Override
	public ArrayList<BoardBeanDto> selectArticleList(int page, int limit) {
		String sql = "select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,"
				+ " BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE" + " from board"
				+ " order by BOARD_RE_REF desc, BOARD_RE_SEQ asc" + " limit ?, ?;";
		int startRow = (page - 1) * limit;// 해당 페이지 시작 위치
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<BoardBeanDto> list = new ArrayList<BoardBeanDto>();
					do {
						list.add(getBoardDTO(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private BoardBeanDto getBoardDTO(ResultSet rs) throws SQLException {
		int board_num = rs.getInt("BOARD_NUM");
		String board_name = rs.getString("BOARD_NAME");
		String board_pass = rs.getString("BOARD_PASS");
		String board_subject = rs.getString("BOARD_SUBJECT");
		String board_content = rs.getString("BOARD_CONTENT");
		String board_file = rs.getString("BOARD_FILE");
		int board_re_ref = rs.getInt("BOARD_RE_REF");
		int board_re_lev = rs.getInt("BOARD_RE_LEV");
		int board_re_seq = rs.getInt("BOARD_RE_SEQ");
		int board_readcount = rs.getInt("BOARD_READCOUNT");
		Date board_date = rs.getDate("BOARD_DATE");
		return new BoardBeanDto(board_num, board_name, board_pass, board_subject, board_content, board_file,
				board_re_ref, board_re_lev, board_re_seq, board_readcount, board_date);
	}

	@Override
	public BoardBeanDto selectArticle(int board_num) {
		String sql = "select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT,"
				+ " BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,"
				+ " BOARD_DATE from board where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getBoardDTO(rs);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public int insertReplyArticle(BoardBeanDto article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateArticel(BoardBeanDto article) {

		return 0;
	}

	@Override
	public int deleteArticle(int board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReadCount(int board_num) {
		String sql = "update board" + " SET BOARD_READCOUNT = BOARD_READCOUNT + 1" + " where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int insertArticle(BoardBeanDto article) {
		String sql = "INSERT INTO web_gradle_erp.board"
				+ " (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextNum);

			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());

			pstmt.setInt(7, nextNum);
			System.out.println(pstmt);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
