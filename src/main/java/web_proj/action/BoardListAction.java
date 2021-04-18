package web_proj.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_proj.dto.ActionForward;
import web_proj.dto.BoardBeanDto;
import web_proj.dto.PageInfo;
import web_proj.service.BoardListService;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//게시판 목록 동작들..
		
		int page = 1;  //초기 페이지
		int limit = 10; //한 페이지 당 게시물 수
		
		if(request.getParameter("page")!= null) {
			//페이지가 null이 아니면 파라미터의 값을 page로 한다.
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		BoardListService service = new BoardListService();
		//게시글 리스트 가져오기
		ArrayList<BoardBeanDto> list = service.getArticleList(page, limit);
		list.stream().forEach(System.out::println);
		
		//총 리스트 개수
		//게시글 카운트 가져오기
		int listCount = service.getListCount();
		//전체 맨 마지막 페이지 == 레코드개수/한페이지당레코드수 (올림 해야함)
		//Math.ceil는 올림기능
		//끝 페이지는 총 리스트에서 한페이지 당 게시물 수 를 나눠서 계산한 값
		int maxPage = (int)Math.ceil((double)listCount/limit);
		//1page 1~5, 2page 6~10, 3page 11~15
		// [이전][다음] [<<] [1]<--stratPage     [2][3][4][5][6][7][8][9][10] [>>]
		// [이전][다음] [<<] [11]<--stratPage    [2][3][4][5][6][7][8][9][10] [>>]
		//현재페이지 나누기 한페이지레코드수 하면 0 거기에서 0.9을 더하면 1
		/*                                                                                                 
		 * 페이지는 1 ~ 10 / 21 ~ 20 / 31 ~ 30 으로 보여진다                                                         
		 * 때문에 startPage에 해당 하는 값을 구하기 위해선 아래와 같은 공식을 쓴다.                                                  
		 * 나누기 10을 한 뒤 0.9를 더해준 이유는 maxPage와 비슷한 이유 때문인데                                                   
		 * 만약 페이지의 일의 자리 수가 0이 아니면 십의자리수가 -1이 되는 페이지가 되어버린다.  
		 * 이를 방지 하기 위해 페이지는 0.1~ 0.9 까지의 결과값이 나올 때 더해서 1이 올라가게 하는 숫자인 0.9를 더하여  
		 * 한자리를 높여주게 되면 모든 결과가 동일한 십의 자리 수를 갖게 된다.                                                         
		 */                                                                                                
		int startPage = (((int)((double)page/10 +0.9))-1)* 10 + 1 ;
//		int startPage = (int)Math.floor(page/10) * 10 + 1 ;
		int endPage = startPage + 9;                                                                       
		if(endPage > maxPage) {                                                                            
		    endPage=maxPage;                                                                               
		}
		System.out.println("listCount = "+listCount + "maxpage = " +maxPage+"<br>"
						   +"startPage = " +startPage + "endPage = "+endPage+"<br>");
		//생성자
		PageInfo pageInfo =new PageInfo(page,maxPage,startPage,endPage,listCount);
		request.setAttribute("articleList", list);
		request.setAttribute("pageInfo", pageInfo);
		//포워딩하기
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
		return forward;
	}

}
