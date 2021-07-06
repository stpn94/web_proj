package web_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.dto.ActionForward;
import web_proj.service.BoardDeleteService;

public class BoardDeleteProAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // boardDeletePro.do?board_num=37
      // hiddenpage=1
      // BOARD_PASS=aaaa
	   PrintWriter out = response.getWriter();
      int board_num = Integer.parseInt(request.getParameter("board_num"));
      String page = request.getParameter("page");
      String pass = request.getParameter("BOARD_PASS");

      BoardDeleteService service = new BoardDeleteService();
      boolean isArticleWriter = service.isArticleWriter(board_num, pass);

      ActionForward forward = null;
      if (!isArticleWriter) {
    	 System.out.println("아놔 왜 따운 받노");
         //sendMessage(response, "삭제할 권한이 없습니다.");
         return forward;
      }
      boolean isDeleteSuccess = service.removeArticle(board_num);
      if (!isDeleteSuccess) {
         sendMessage(response, "삭제실패");
         return forward;
      }
      forward = new ActionForward();
      forward.setRedirect(true);
      forward.setPath("boardList.do?page=" + page);
      System.out.println(1);
      return forward;
   }


   private void sendMessage(HttpServletResponse response, String msg) throws IOException {
	   System.out.println("아아아아아ㅏㅇ");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('" + msg + "');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
   }

}