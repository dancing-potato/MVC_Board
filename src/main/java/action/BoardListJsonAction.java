package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListJsonAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호를 저장할 변수(기본값 1)
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록 수를 저장할 변수(기본값 10) 
		
		// 전달된 파라미터 중 "pageNum" 파라미터가 null 이 아닐 경우
		// 해당 파라미터값을 pageNum 변수에 저장
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		System.out.println(pageNum);
		
		// BoardListService 클래스 인스턴스 생성 후 getListCount() 메서드를 호출하여
		// 전체 게시물 갯수 리턴받기
		// => 파라미터 : 없음   리턴타입 : int(listCount)
		BoardListService service = new BoardListService();
		int listCount = service.getListCount();
//				System.out.println("총 게시물 수 : " + listCount);
		
		// BoardListService 객체의 getBoardList() 메서드를 호출하여 전체 게시물 목록 가져오기
		// => 파라미터 : 페이지번호(pageNum), 페이지 당 목록 갯수(listLimit)
		//    리턴타입 : List<BoardBean>(boardList)
		List<BoardBean> boardList = service.getBoardList(pageNum, listLimit);
		
		// -------------------------------------------------------------------
		// 페이지 계산 작업 수행
		// 한 페이지에서 표시할 페이지 갯수 설정
		int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 10개로 제한
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징 처리 정보를 저장하는 PageInfo 클래스 인스턴스 생성 및 데이터 저장
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		// --------------------------------------------------------------------------------
		// request 객체에 뷰페이지로 전달할 데이터(객체) 저장 = request.setAttribute()
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfo", pageInfo);
//				System.out.println(boardList);
		
		// ActionForward 객체 생성 후 board 폴더의 qna_board_list_part.jsp 페이지 포워딩 설정
		// => list_part.jsp 페이지에서 새로 로딩한 다음 페이지 글목록을 표시(응답)
		//    (응답데이터가 AJAX 를 통해 기존 페이지(qna_board_list.jsp) 내의 아래쪽에 추가됨)
		forward = new ActionForward();
		forward.setPath("board/qna_board_list_part.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}







