package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// HttpSession 객체 가져와서 invalidate() 호출하여 세션 초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		// ActionForward 객체를 통해 메인페이지로 포워딩
		forward = new ActionForward();
		forward.setPath("./");
		forward.setRedirect(true);
		
		return forward;
	}

}










