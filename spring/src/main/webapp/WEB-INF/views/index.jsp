<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Home" name="title"/>
</jsp:include>
<section id="content">
	<h2>${greeting }</h2>
	<h3>${msg }</h3>
	<h3>ajax통신하기</h3>
	<button class="btn btn-outline-primary" onclick="basicAjax()">
		servlet방식으로 응답하기
	</button>
	<button class="btn btn-outline-primary" onclick="responseBodyAjax()">
		@ResponseBody이용하기
	</button>
	<button class="btn btn-outline-primary" onclick="demoListRequest()">
		demolist
	</button>
	<!-- 
		회원 아이디를 입력받고 아이디가 일치하는 회원 ajax로 가져와 출력하기
		<table> <ul>
	  -->
	  <div id="memberId-search">
		  <input type="text" name="userId">
		  <button class="btn btn-outline-primary" onclick="getMember()">
		  회원조회
		  </button>
	  </div>
	<div id="result"></div>
	<script>
		const getMember=()=>{
			const userId=document
					.querySelector("#memberId-search>input[name=userId]")
					.value;
			if(userId.length>=4){
				//fetch("${path}/ajax/member?userId="+userId)
				fetch(`${path}/ajax/member?userId=\${userId}`)
				.then(response=>response.json())
				.then(data=>{
					console.log(data);
					const $table=document.createElement("table");
					const $header=document.createElement("tr");
					for(let h in data){
						const $th=document.createElement("th");
						$th.innerText=h;
						$header.appendChild($th);
					}
					const $body=document.createElement("tr");
					for(let h in data){
						const $td=document.createElement("td");
						if(h==='enrollDate'){
							const d=new Date(data[h]);
							$td.innerText=d.getFullYear()
											+"-"+(d.getMonth()+1)+"-"+d.getDate();
						}else{
							$td.innerText=data[h];
						}
						$body.appendChild($td);
					}
					$table.appendChild($header);
					$table.appendChild($body);
					document.querySelector("#result").appendChild($table);
				});
			}
		}
		const demoListRequest=()=>{
			fetch("${path}/ajax/demos")
			.then(response=>response.json())
			.then(data=>{
				console.log(data);
			});
		}
		const responseBodyAjax=()=>{
			fetch("${path}/ajax/lunches")
			.then(response=>response.json())
			.then(data=>{
				console.log(data);
			});
		}
		const basicAjax=()=>{
			fetch("${path}/ajax/basicajax")
			/* .then(response=>response.text()) */
			.then(response=>response.json())
			.then(data=>{
				console.log(data);
				const $ul=document.createElement("ul");
				data.forEach(e=>{
					const $li=document.createElement("li");
					$li.innerText=e;
					$ul.appendChild($li);
				});
				document.querySelector("#result")
				.appendChild($ul);
			});
		}
	
	</script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
