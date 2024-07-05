<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>
<div id="board-container">
        <form name="boardFrm" action="${path}/board/insertboard.do"
         method="post" enctype="multipart/form-data">
            <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required>
            <input type="text" class="form-control" placeholder="아이디 (4글자이상)"
             name="boardWriter" value="${sessionScope.loginMember.userId}" readonly required>
            <div class="input-group mb-3" style="padding:0px;">
                <div class="input-group mb-3" style="padding:0px;">
                	<div class="input-group-prepend" style="padding:0px">
                		<button type="button" class="btn btn-outline-primary"
                		onclick="fn_addFile();">
                		추가
                		</button>
                		<button type="button" class="btn btn-outline-danger"
                		onclick="fn_delFile();">
                		삭제
                		</button>
                	</div>
                </div>
                <div id="basicFileForm" class="input-group mb-3" style="padding:0px">
                	<div class="input-group-prepend" style="padding:0px;">
                		<span class="input-group-text">첨부파일1</span>
                	</div>
	                <div class="custom-file">
	                    <input type="file" class="custom-file-input" name="upFile" id="upFile1">
	                    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
	                </div>
	           	</div>
            </div>
            <textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
            <br />
            <input type="submit" class="btn btn-outline-success" value="저장" >
        </form>
    </div>

    <style>
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input{margin-bottom:15px;}
    </style>
    <script>
    	const addDelFunction=(function(){
    		let count=2;
    		const addFileform=()=>{
    			if(count<=5){
    				const fileForm=$("#basicFileForm").clone(true);
    				fileForm.find("span.input-group-text")
    					.text("첨부파일"+count);
    				fileForm.find("label.custom-file-label")
    					.text("파일을 선택하세요")
    					.attr("for","upFile"+count);
    				fileForm.find("input[type=file]")
    					.attr("id","upFile"+count).val("");
    				$("textarea[name=boardContent]").prev().append(fileForm);
    				count++;
    			}else{
    				alert("첨부파일은 5개까지 가능합니다");
    			}
    		}
    		const delFileform=()=>{
    			if(count!=2){
    				$("textarea[name=boardContent]").prev().children().last().remove();
    				count--;
    			}
    		}
    		return {addFileform,delFileform};
    	})();
    	const fn_addFile=addDelFunction.addFileform;
    	const fn_delFile=addDelFunction.delFileform;
    	
    	$("input[name=upFile]").change(e=>{
    		const fileName=e.target.files[0].name;
    		$(e.target).next(".custom-file-label").text(fileName);
    	});
    </script>
    
    
    
    
    
    
    
    
    
    
    
    
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>