<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- JSTL 과 EL 활용하여 글목록 표시 -->
<c:forEach var="board" items="${boardList }">
	<tr height="50">
		<td>${board.board_num }</td>
		<td class="td_left">
			<%-- =============== 답글 관련 목록 처리 추가 ================== --%>
			<%-- board_re_lev 값이 0보다 크면 반복문을 통해 해당 값만큼 공백(&nbsp;) 추가 --%>
			<c:if test="${board.board_re_lev > 0 }">
				<%-- c:forEach 문을 통해 1부터 board_re_lev 값까지 1씩 증가하면서 공백 추가 --%>
				<c:forEach var="i" begin="1" end="${board.board_re_lev }">
					&nbsp;
				</c:forEach>
				<%-- 답글 제목 앞에 이미지 추가 --%>
				<img src="images/re.gif">
			</c:if>
			<%-- ============================================================ --%>
			<a href="BoardDetail.bo?board_num=${board.board_num }&pageNum=${pageInfo.pageNum}" title="${board.board_subject }">
				${board.board_subject }</a>
		</td>
		<td title="${board.board_name }">${board.board_name }</td>
		<td>${board.board_date }</td>
		<td>${board.board_readcount }</td>
	</tr>
</c:forEach>
