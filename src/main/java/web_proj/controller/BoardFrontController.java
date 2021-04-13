package web_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.action.Action;
import web_proj.dto.ActionForward;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String RequestURI=request.getRequestURI();
//		String contextPath=request.getContextPath();
//		String command2=RequestURI.substring(contextPath.length());
//		System.out.println("리퀘스트 URI>>"+RequestURI);
//		System.out.println("컨텍스트패스>>" + contextPath);
//		System.out.println("커맨드2>>" + command2); //이게 필요함
		String command = request.getServletPath(); // morethen
		System.out.println("커맨드>>" + command); //이게 필요함
		ActionForward forward = null; //캡슐화한거임
		Action action=null;
		
		if(command.equals("/boardWriteForm.do")) {
//			request.getRequestDispatcher("/board/qna_board_write.jsp").forward(request, response);
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		}
		if(forward !=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
