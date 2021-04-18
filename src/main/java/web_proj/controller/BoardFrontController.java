package web_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.action.Action;
import web_proj.action.BoardDetailAction;
import web_proj.action.BoardListAction;
import web_proj.action.BoardReplyFormAction;
import web_proj.action.BoardWriteProAction;
import web_proj.dto.ActionForward;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);

		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String RequestURI=request.getRequestURI();
//		String contextPath=request.getContextPath();
//		String command2=RequestURI.substring(contextPath.length());
//		System.out.println("리퀘스트 URI>>"+RequestURI);
//		System.out.println("컨텍스트패스>>" + contextPath);
//		System.out.println("커맨드2>>" + command2); //이게 필요함
		String command = request.getServletPath(); // morethenabove
		System.out.println("커맨드>>" + command); // 이게 필요함
		ActionForward forward = null; // path와 redirect 꺼내오기 위해 캡슐화한거임
		Action action = null;

		if (command.equals("/boardWriteForm.do")) {
//			request.getRequestDispatcher("/board/qna_board_write.jsp").forward(request, response);
			System.out.println("boardWriteForm.do>>qna_board_write.jsp");
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");

		} else if (command.equals("/boardWritePro.do")) {
//			BOARD_NAME=lee&BOARD_PASS=lee&BOARD_SUBJECT=lee&BOARD_CONTENT=lee&BOARD_FILE=바위.jpg
			System.out.println("boardWritePro.do>>등록>>BoardWriteProAction에 있는 execute forward>>boardList.do");
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/boardList.do")) {
			System.out.println("boardList.do>>>>게시판 보기");
//			System.out.println("boardListdo>>>>>>>>>>>");
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/boardDetail.do")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (command.equals("/boardReplyForm.do")) {
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (command.equals("/boardReplyPro.do")) {
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (forward != null)

		{
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
