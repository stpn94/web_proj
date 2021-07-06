package web_proj.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import web_proj.JdbcUtil;
import web_proj.dao.impl.BoardDaoImpl;
import web_proj.dto.BoardBeanDto;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void test02SelectListCount() {
		System.out.println("testSelectListCount");
		int res = dao.selectListCount();
		
		Assert.assertNotEquals(-1, res);
		System.out.println("ListCount >>"  + res);
	}

	@Test
	public void test01NextBoardNum() {
		System.out.println("testNextBoardNum");
		int res = dao.nextBoardNum();
		Assert.assertNotNull(res);
		System.out.println(res);
	}

	@Test
	public void test03SelectArticleList() {
		System.out.println("testSelectArticleList");
		int page = 1;
		int limit = 4;
		ArrayList<BoardBeanDto> list = dao.selectArticleList(page, limit);
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
		
		for(BoardBeanDto b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void test05SelectArticle() {
		System.out.println("test05SelectArticle");
		BoardBeanDto board = dao.selectArticle(1);
		Assert.assertNotNull(board);
		System.out.println(board);
	}

//	@Test
//	public void test04InsertArticle() {
//		System.out.println("testInsertArticle");
//		BoardBeanDto newBoard = new BoardBeanDto("1234", "1234", "5시 퇴근가능할까", "불가능 함123123123", "test.txt");
//		int res = dao.insertArticle(newBoard);
//		Assert.assertEquals(1, res);
//
//	}
	
	@Test
	public void test04InsertArticle() {
		System.out.println("testInsertArticle");
		BoardBeanDto article = new BoardBeanDto("김경연", "1234", "5시퇴근은 가능할까?", "불가능함", "test.txt");
		int res = dao.insertArticle(article);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testInsertReplyArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void test06UpdateArticel() {
		System.out.println("test06UpdateArticel");
		int board_num=58;
		int res = dao.updateReadCount(board_num);
		Assert.assertEquals(1, res);
		System.out.println("res ==>>"+ res);
	}
	@Test
	public void testisarticleboardWriter() {
		System.out.println("isarticleboardWriter");
		int board_num = 22;
		boolean res = dao.isArticleBoardWriter(board_num, "sdf");
		Assert.assertEquals(true, res);
		System.out.println("res >> " + res);
	}
	@Test
	public void test07DeleteArticle() {
		System.out.println("test07DeleteArticle()");
	      int board_num = dao.nextBoardNum() -1;
	      int res = dao.deleteArticle(board_num);
	      Assert.assertEquals(1, res);
	      System.out.println("res >>" +res);
	}
	

	@Test
	public void testUpdateReadCount() {
		fail("Not yet implemented");
	}

}
