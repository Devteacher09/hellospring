<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        <div style="display:flex;width:100%;justify-content:end;">
        	<button class="btn btn-outline-dark"
        	onclick="location.assign('${path}/board/inputboard.do')">글쓰기</button>
        </div>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards }">
            	<c:forEach var="b" items="${boards }">
					<tr>
						<td><c:out value="${b.boardNo }"/></td>
						<td>
							<a href="${path }/board/boardview.do?boardNo=${b.boardNo}">
								<c:out value="${b.boardTitle }"/>
							</a>
						</td>
						<td><c:out value="${b.boardWriter }"/></td>
						<td><c:out value="${b.boardDate }"/></td>
						<td>
							<c:if test="${b.files.size()>0&&b.files.get(0).attachNo!=0}">
								<img src="${path }/resources/images/file.png"
								width="25"/>
							</c:if>
						</td>
						<td><c:out value="${b.boardReadcount }"/></td>
					</tr>
            	</c:forEach>
            </c:if>
        </table> 
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>