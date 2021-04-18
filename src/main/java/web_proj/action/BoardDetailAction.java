package web_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.dto.ActionForward;
import web_proj.dto.BoardBeanDto;
import web_proj.service.BoardDetailService;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println(board_num);
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println(page);

		BoardDetailService service = new BoardDetailService();
		BoardBeanDto article = service.getArticle(board_num);

		request.setAttribute("page", page);
		request.setAttribute("article", article);

		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_view.jsp");
		return forward;
	}

}
