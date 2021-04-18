package web_proj.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web_proj.dto.ActionForward;
import web_proj.dto.BoardBeanDto;
import web_proj.service.BoardWriteService;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String realFolder = "";
		String saveFolder = "/boardUpload";
		int fileSize = 5 * 1024 * 1024;// 5M

		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);

		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());

		BoardBeanDto boardDTO = new BoardBeanDto();
		boardDTO.setBoard_name(multi.getParameter("BOARD_NAME"));
		boardDTO.setBoard_pass(multi.getParameter("BOARD_PASS"));
		boardDTO.setBoard_subject(multi.getParameter("BOARD_SUBJECT"));
		boardDTO.setBoard_content(multi.getParameter("BOARD_CONTENT"));
		boardDTO.setBoard_file(multi.getOriginalFileName(((String) multi.getFileNames().nextElement())));
		// BoardWriteService생성
		BoardWriteService service = new BoardWriteService();
		// 게시물 등록
		boolean result = service.registerArticle(boardDTO);
		ActionForward forward=null;

		// 게시물 등록 성공하면
		if (result) {
			forward = new ActionForward();
			forward.setRedirect(true);
			// Controller의 boardList.do 실행
			forward.setPath("boardList.do");
		} else {
			// 등록실패하면 등록실패 시키고 뒤로가기
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
			
		}


		System.out.println("realFolder(경로)>>" + realFolder);
		return forward;
	}

}
