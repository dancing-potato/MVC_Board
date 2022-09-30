package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinProAction");
		
		ActionForward forward = null;
		
		// 파라미터(이름, 성별, E-Mail, 아이디, 패스워드) 가져와서 MemberBean 객체에 저장
		MemberBean member = new MemberBean();
		member.setName(request.getParameter("name"));
		member.setGender(request.getParameter("gender"));
		member.setEmail(request.getParameter("email1") + "@" + request.getParameter("email2"));
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
//		System.out.println(member);
		
		// MemberJoinProService - registMember() 메서드 호출하여 회원 등록 작업 요청
		// => 파라미터 : MemberBean 객체    리턴타입 : boolean(isRegistSuccess)
		// => MemberDAO 객체의 insertMember() 메서드 호출하여 회원 등록 처리
		//    (회원번호 idx 는 auto_increment 컬럼이므로 null 값 전달)
		
		
		// 회원 등록 작업 요청 결과 판별
		// 실패 시 자바스크립트를 통해 "회원 가입 실패!" 출력 후 이전페이지 돌아가기
		// 아니면 ActionForward 객체 사용하여 "MemberJoinResult.me" 주소 요청(Redirect 방식)
		// => member_join_result.jsp 페이지로 연결
		
		
		
		return forward;
	}

}













