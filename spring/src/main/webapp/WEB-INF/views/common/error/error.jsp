<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>
<section>
	<h3>에러 페이지!</h3>
	<p style="color:red;font-size:30px;font-weight:bolder;">
		<%=exception.getMessage() %>
	</p>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>