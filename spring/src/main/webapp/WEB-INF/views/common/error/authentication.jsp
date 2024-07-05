<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="에러페이지"/>
</jsp:include>
<section>
	<div style="display:flex;width:100%;justify-content:center;">
		<img src="${path }/resources/images/notaccess.jpg" width="300">	
	</div>
	<h3 style="color:red;"><%=exception!=null?exception.getMessage():"에러임"%></h3>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>