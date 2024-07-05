<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div style="width:100%;height:500px;
		justify-content:center;display:flex;align-items:center;" >
		<div style="width:30%">
			<h3>로그인</h3>
			<form action="${pageContext.request.contextPath }/loginbs" method="post">
				<div class="modal-body">
					<input type="text" name="userId" class="form-control"
					placeholder="아이디입력" required><br/>
					<input type="password" name="pw" class="form-control"
					placeholder="패스워드입력" required>	<br/>
					<label><input type="checkbox" name="saveUser">로그인유지</label>										
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-outline-success">로그인</button>
					<button type="button" class="btn btn-outline-success"
					data-dismiss="modal">취소</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>