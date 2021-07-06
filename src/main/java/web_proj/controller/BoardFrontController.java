package web_proj.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.action.Action;
import web_proj.action.BoardDeleteProAction;
import web_proj.action.BoardDetailAction;
import web_proj.action.BoardListAction;
import web_proj.action.BoardReplyFormAction;
import web_proj.action.BoardWriteProAction;
import web_proj.action.FileDownAction;
import web_proj.dto.ActionForward;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }

   private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      String RequestURI = request.getRequestURI();
//      String contextPath = request.getContextPath();
//      String command=RequestURI.substring(contextPath.length());   
//      System.out.println(RequestURI + " >> " + contextPath + " >> " + command);

      String command = request.getServletPath(); //3줄코드를 한번에 압축
      System.out.println(command);
      
      ActionForward forward = null;
      Action action = null;
    
      
      if(command.equals("/boardWriteForm.do")) {
//         request.getRequestDispatcher("/board/qna_board_write.jsp").forward(request, response);
         forward = new ActionForward();
         forward.setPath("/board/qna_board_write.jsp");
      }else if(command.contentEquals("/boardWritePro.do")) {
         action = new BoardWriteProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (command.equals("/boardList.do")) {
         action = new BoardListAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (command.equals("/boardDetail.do")) {
         action = new BoardDetailAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (command.equals("/boardReplyForm.do")) {
         action = new BoardReplyFormAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (command.equals("/boardDeleteForm.do")) {
         //board_num=36&page=1
         String nowPage = request.getParameter("page");
         request.setAttribute("page", nowPage);
         
         int board_num = Integer.parseInt(request.getParameter("board_num"));
         request.setAttribute("board_num", board_num);
         
         forward = new ActionForward();
         forward.setPath("/board/qna_board_delete.jsp");
      } else if (command.equals("/boardDeletePro.do")) {
         //boardDeletePro.do?board_num=37&page=1&Board_Pass=aaa
         action = new BoardDeleteProAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else if (command.equals("/fild_down.do")) {
         action = new FileDownAction();
         try {
            forward = action.execute(request, response);
         } catch (Exception e) {
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
      
      
      
      if(forward != null) {
         if(forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
         } else {
            request.getRequestDispatcher(forward.getPath()).forward(request, response);
         }
      }
      
   }

}