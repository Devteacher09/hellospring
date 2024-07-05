<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demo리스트"/>
</jsp:include>
<section id="content">
	<button class="btn btn-outline-success"
	onclick="location.assign('${pageContext.request.contextPath}/demo/excelfile')">
		엑셀다운
	</button>
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정</th>
		</tr>
		<c:if test="${not empty demos }">
			<c:forEach var="d" items="${demos }">
				<tr>
					<td scope="col">${d.devNo }</td>
					<td scope="col">${d.devName }</td>
					<td scope="col">${d.devAge }</td>
					<td scope="col">${d.devEmail }</td>
					<td scope="col">${d.devGender }</td>					
					<td scope="col">
						<c:if test="${not empty d.devLang }">
							<ul>
							<c:forEach var="l" items="${d.devLang }">
								<li>${l }</li>
							</c:forEach>
							</ul>
						</c:if>
					</td>
					<td>
						<button class="btn btn-outline-success">수정</button>
						<button class="btn btn-outline-danger">삭제</button>
					</td>					
				</tr>
			</c:forEach>
		</c:if>
	</table>	
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>