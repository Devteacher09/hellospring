<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메모리스트"/>
</jsp:include>
<style>
    div#memo-container{width:60%; margin:0 auto;}
    .error{color:magenta;font-weight:bolder;}
    </style>
    <div id="memo-container">
        <springform:form modelAttribute="memo" 
        	action="${path }/memo/insertmemo.do" method="post" 
        class="form-inline">
            <springform:input path="memo" type="text" class="form-control col-sm-6" name="memo" placeholder="메모"/>&nbsp;
            <springform:errors path="memo" cssClass="error"/>
            <springform:input path="password" type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" />&nbsp;
            <springform:errors path="password" cssClass="error"/>
            <button class="btn btn-outline-success" type="submit" >저장</button>
        </springform:form>
    </div>
<br />
        <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
            <c:if test="${not empty memos }">
            	<c:forEach var="memo" items="${memos }">
            		<tr>
            			<td>${memo.memoNo }</td>
            			<td>${memo.memo }</td>
            			<td>${memo.memoDate }</td>
            			<td>
            				<button class="btn btn-outline-success">수정</button>
            				<button class="btn btn-outline-danger">삭제</button>
            			</td>
            		</tr>
            	</c:forEach>
            </c:if>
        </table>
		<div id="pageBar">
			${pageBar }
		</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>