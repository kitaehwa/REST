<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>실행 : http://localhost:8088/</h1>
	<h1>뷰 : /views/boardTest.jsp</h1>

	<h2>게시판 CRUD - REST방식으로</h2>

	<h2>게시판 글쓰기</h2>

	<form action="">
		제목 : <input type="text" name="title" id="title"><br>
		작성자 : <input	type="text" name="writer" id="writer"><br>
		내용 : <input type="text" name="content" id="content"><br>
		<input type="button" id="btnCreate"	value="글쓰기(Create)">
	</form>

	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>
	<script type="text/javascript">
	  $(function(){
		  // 1) 글쓰기 버튼 클릭시, 글쓰기 수행(REST)
	    		
	  
	  	$("#btnCreate").click(function(){
	  		
	  		var board ={
	  			//"bno" : 100,
	  			"title" : $("#title").val(),
	  			"content" : $("#content").val(),
	  			"writer" : $("#writer").val()
	  		};
	  		
	  		$.ajax({
	  			url : "${contextPath}/boards",
	  			type : "POST",
	  			data : JSON.stringify(board),
	  			contentType : "application/json",
	  			success : function(data){
	  				alert(" 성공! ");
	  				$('body').append(data);
	  				if(data == 'ADD_Success'){
	  					alert(" 글쓰기 성공! ");
	  					$("#title").val("");
	  					$("#content").val("");
	  					$("#writer").val("");
	  				}
	  			}	  			
	  		});
	  		
	  	});//"#btnCreate".click
	  });//jquery	
	</script>
	
	<hr>
	
	<h2>게시판 목록(list)</h2>
	
	<input type="button" value="게시판 조회" id="btnRead">
	
	<div id="divRead"></div>
	
	<script type="text/javascript">
		$(function(){
			$("#btnRead").click(function(){
				//alert(" 리스트 ");
				// 정보를 REST방식 호출
				// 글조회 : /boards/all  GET방식(ALL)
				// => 결과를 divRead에 저장
				$.ajax({
					url : "/boards/all",
					type : "GET",
					success :function(data){
						//alert("성공!");	
						//alert(data);
						console.log(data);	
					
						$(data).each(function(idx,item){
							console.log(idx);
							console.log(item);
							$("#divRead").append(function(){
								
								return "<a>"+item.bno+"//"+item.title+"</a><br>"							
							});								
						});						
					}					
				});// ajax
			});	//click
		});		
	</script>
	
	<hr>
	<h2> 게시판 특정 글 조회(READ) </h2>
	
	<!-- bno=1 인 게시판 글의 정보를 조회 -->
	<input type="button" value="글조회" id="btnReadBno">
	 
	<div id="divReadBno"></div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
// 				url : "/boards/"+bno	
				url : "/boards/1",
				type : "GET",
				success : function(data){
					console.log(data);
					$("#divReadBno").append(
						"<h3>"+
						 data.bno
						+ "," +
						 data.title
						+ "," +
						data.writer
						+ "," +
						data.content
						+ "," +
						new Date(data.regdate)
						+"</h3>"
					);
					
				}
			});		
		});
	</script>
	
	<hr>
	<h2>게시판 글 수정</h2>

	<form action="">
		제목 : <input type="text" name="title" id="utitle"><br>
		작성자 : <input	type="text" name="writer" id="uwriter"><br>
		내용 : <input type="text" name="content" id="ucontent"><br>
		<input type="button" id="btnUpdate"	value="글 수정(Update)">
	</form>
	
	<script type="text/javascript">
		$(function(){
			$("#btnUpdate").click(function(){
				
				var updateData = {
					"bno" : 1,
					"title" : $("#utitle").val(),
					"writer" : $("#uwriter").val(),
					"content" : $("#ucontent").val()
				};
				console.log(updateData);
				
				$.ajax({
					url : "/boards/1",
					type : "PUT",
					contentType : "application/json",
					data : JSON.stringify(updateData),
					success : function(data){
						console.log(data);
					}
					
				});
				
			});					
		});
	</script>
	
	<hr>
	
	<h2> 게시판 글 삭제 </h2>
	
	<input type="button" value="글삭제" id="btnDelete">
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btnDelete").click(function(){
				
				$.ajax({
					url: "/boards/1",
					type: "DELETE",
					success: function(data){
						console.log(data);
						
					}
				});
			});
		});
	
	</script>




</body>
</html>