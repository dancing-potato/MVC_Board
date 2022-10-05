package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberAuthService;
import vo.ActionForward;
import vo.AuthInfoBean;

public class MemberAuthAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 아이디, 인증코드 파라미터 가져와서 AuthInfoBean 객체에 저장
		AuthInfoBean authInfo = new AuthInfoBean();
		authInfo.setId(request.getParameter("id"));
		authInfo.setAuth_code(request.getParameter("authCode"));
		
		// MemberAuthService - checkAuthInfo() 메서드 호출하여 인증 정보 확인 요청
		// => 파라미터 : AuthInfoBean 객체    리턴타입 : boolean(isAuthenticationSuccess)
		MemberAuthService service = new MemberAuthService();
		boolean isAuthenticationSuccess = service.checkAuthInfo(authInfo);
		
		// 인증 정보 확인 결과 판별
		// => 인증 실패 시 자바스크립트를 통해 "인증 실패! 인증 정보를 확인하세요!" 출력 후
		//    이전 페이지로 돌아가기
		// => 아니면, 자바스크립트를 통해 "인증 성공! 로그인 페이지로 이동합니다!" 출력 후
		//    로그인 페이지("MemberLoginForm.me") 서블릿 주소 요청
		if(!isAuthenticationSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('인증 실패!\n인증 정보를 확인하세요!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('인증 성공!\n로그인 페이지로 이동합니다!')");
			out.println("MemberLoginForm.me");
			out.println("</script>");
		}
		
		
		return forward;
	}

}












