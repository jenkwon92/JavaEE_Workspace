<%@ page contentType="text/html;charset=utf-8"%>
<%
	int totalRecord = 26; //총레코드수
	int pageSize = 10; //한 페이지당 보여질 레코드 수
	int totalPage = ((int) Math.ceil((float) totalRecord / pageSize));
	int blockSize = 10; //한블럭당 보여질 페이지 수
	int currentPage=1; //현재페이지
	currentPage = Integer.parseInt(request.getParameter("currentPage"));
	//int firstPage = ((int)Math.floor((float)currentPage/blockSize)*blockSize)+1; //반복문의 시작 값
	//int lastPage = (int)Math.floor((float)currentPage/blockSize)*blockSize; //반복문의 끝 값
	int firstPage = currentPage - (currentPage-1)%blockSize; //반복문의 시작 값
	int lastPage = firstPage +(blockSize-1);  //반복문의 끝 값
	int num =totalRecord-(currentPage-1)*pageSize;//페이지당 시작 번호(힌트: 위에있는 모든 변수를 조합하면 구할 수 있음)
	
%>
<%="totalRecord :" + totalRecord + "<br>"%>
<%="pageSize :" + pageSize + "<br>"%>
<%="totalPage :" + totalPage + "<br>"%>
<%="blockSize :" + blockSize + "<br>"%>
<%="currentPage :" + currentPage + "<br>"%>
<%="firstPage :" + firstPage + "<br>"%>
<%="lastPage :" + lastPage + "<br>"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

img {
	box-sizing: border-box;
}

a {
	text-decoration: none;
}

.pageNum{
	font-size:20pt;
	color:red;
	font-weight:bold;
}
</style>
<script>
	
</script>
</head>
<body>
	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%for (int i = 1; i <= pageSize; i++) {%>
		<tr>
		<%if(num<1)break; %>
			<td><%=num-- %></td>
			<td>제목입니다</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<%}%>

		<tr>
			<td colspan="5" style="text-align: center">
				<%if(firstPage-1>0) {//페이지가 있다면%> 
					<a href="/qna/list2.jsp?currentPage=<%=firstPage-1%>">◀</a>
				<%}else{ %>
					<a href="javascript:alert('처음페이지입니다')">◀</a>
				<%} %>
				
				<%for(int i = firstPage; i <= lastPage; i++) {%> 
					<%if(i>totalPage)break; //페이지를 출력하는 i가 총페이지수 넘어설 때 반복문 빠져나와라%>
					<a href="/qna/list2.jsp?currentPage=<%=i%>" <%if(currentPage == i){ %>class="pageNum"<%} %>>[<%=i%>] </a>	
				<%}%>
				<%if(lastPage+1<totalPage) {//페이지가 있다면%> 
					<a href="/qna/list2.jsp?currentPage=<%=lastPage+1%>">▶</a>
				<%}else{ %>
					<a href="javascript:alert('마지막페이지입니다')">▶</a>
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<button>글등록</button>
			</td>
		</tr>
		<tr>
			<td colspan="5" style="text-align: center"><%@ include
					file="/inc/footer.jsp"%></td>
		</tr>
	</table>
</body>
</html>