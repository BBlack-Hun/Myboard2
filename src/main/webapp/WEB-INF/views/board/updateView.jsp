<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판 리스트</title>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "readView?no=${update.no}&inc=1";
// 				location.href = "/board/index";
			})
			
			$(".update_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/board/update");
				formObj.attr("method", "post");
				formObj.submit();
			})
		})
		function fn_valiChk(){
			var updateForm = $("form[name='updateForm'] .chk").length;
			for(var i = 0; i<updateForm; i++){
				if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null){
					alert($(".chk").eq(i).attr("title"));
					return true;
				}
			}
		}
	
</script>
<body>
	<form name="updateForm" action="/board/update" method="post">
		<input type="hidden" id="no" name="no" value="${update.no}" readonly="readonly"/>
		
		<label for="title">제목</label>
		<input type="text"  name="title" class="chk" value="${update.title}"/>
		<label for="content">내용</label>
		<input type="text" name="content" class="chk" value="${update.content}"/>
		<label for="writer">글쓴이</label>
		<input type="text" name="writer" value="${update.writer}" readonly="readonly"/>
		
		<button type="summit" class="update_btn">수정</button>
		<button type="summit" class="cancel_btn">취소</button>
	</form>
</body>
</html>