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

	// 글의 개수 구하기.
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

	// 글 목록 보기.
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

	// 글 내용 보기.
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

//	//글 답변.
	public int insertReplyArticle(BoardBeanDto article) {
		/*
		 * 답변 글 등록처리 1번째쿼리 게시글 번호 최고값을 구해온다. (새로운 글은 항상 글번호가 최신이 되어야 하므로) 2번째 쿼리에서 출력순위를
		 * 한단계 높여준다.(원본 글 그룹이 같고, 출력순서가 현재 글보다.. 높다면..?) 3번째 쿼리에서 구한값들을 이용해서 추가 해준다.
		 */
		// 현재 답변 글의 seq값은 답변 대상 글의 값에 1을 증가시킨다. (현재 답변 글 바로 아래 올수 있게)
		// 레벨단게를 1증가시킨다. (찍은 글의 답변이니까)

		/*
		 * if(updateCount > 0){ commit(con); }
		 */
		replyUpdate(article);
		String sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
				+ "BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,"
				+ "BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,now())";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			int num = nextBoardNum();
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, ""); // 답장에는 파일을 업로드하지 않음.
			pstmt.setInt(7, article.getBoard_re_ref());
			pstmt.setInt(8, article.getBoard_re_lev()+1);
			pstmt.setInt(9, article.getBoard_re_seq()+1);
			pstmt.setInt(10, article.getBoard_readcount());
			return pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("boardReply 에러 : " + ex);
		}
		return 0;
	}

	private void replyUpdate(BoardBeanDto article){
		String sql = "update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? and BOARD_RE_SEQ>? ";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, article.getBoard_re_ref());
			pstmt.setInt(2, article.getBoard_re_seq());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 답글 업데이트.

	// 글 수정.
	@Override
	public int updateArticel(BoardBeanDto article) {

		return 0;
	}

	// 글 삭제.
	@Override
	public int deleteArticle(int board_num) {
		String sql = "delete" + "  from board" + " where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 조회수 업데이트.
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

	// 글 등록.
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

	public boolean isArticleBoardWriter(int board_num, String pass) {
		String sql = "select 1 from board " + "  where board_num = ? " + " and BOARD_PASS = ? ";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
