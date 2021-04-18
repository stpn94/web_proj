package web_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.dto.ActionForward;
import web_proj.dto.BoardBeanDto;
import web_proj.service.BoardDetailService;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// board_view 에서 [답글]을 누르고 board_num와&page를 파라미터로 가지고 컨트롤러 거치고 여기왔다.

		ActionForward forward = new ActionForward();
		String nowPage = request.getParameter("page");
		int board_num = Integer.parseInt(request.getParameter("board_num"));

		BoardDetailService boardDetailService = new BoardDetailService();
		BoardBeanDto article = boardDetailService.getArticle(board_num);
		// 조회수 증가
		// board_num에 해당하는 BoardDto(게시글 내용들) 을 article에 넣었다.
		
		//article 내용들을 속성으로 담는다.
		request.setAttribute("article", article);
		//page를 속성으로 담는다.
		request.setAttribute("page", nowPage);
		//qna_board_reply.jsp로 forward 한다
		forward.setPath("/board/qna_board_reply.jsp");
		return forward;

	}

}
