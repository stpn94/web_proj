package web_proj.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import web_proj.JdbcUtil;
import web_proj.dao.impl.BoardDaoImpl;
import web_proj.dto.BoardBeanDto;

public class BoardDaoTest {
	private static Connection con = JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectListCount() {
		fail("Not yet implemented");
	}
	@Test
	public void testNextBoardNum() {
		System.out.println("testNextBoardNum");
		int res = dao.nextBoardNum();
		Assert.assertNotNull(res);
		System.out.println(res);
	}

	@Test
	public void testSelectArticleList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertArticle() {
		System.out.println("testInsertArticle");
		BoardBeanDto newBoard = new BoardBeanDto(
				dao.nextBoardNum(),
				"김상건",
				"1234",
				"5시 퇴근가능할까",
				"불가능 함",
				"test.txt",
				0,
				0,
				0,
				0,
				null);
		int res= dao.insertArticle(newBoard);
		Assert.assertEquals(1, res);
		
	}

	@Test
	public void testInsertReplyArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateArticel() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateReadCount() {
		fail("Not yet implemented");
	}

}
