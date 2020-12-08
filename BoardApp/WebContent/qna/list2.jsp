<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//DB 연동
	QnADAO dao = new QnADAO();
	List<QnA> list = dao.selectAll();

	int totalRecord = list.size(); //총레코드수 , 실제 DB에 있는 데이터 수를 대입하면 됨
	int pageSize = 10; //한 페이지당 보여질 레코드 수
	int totalPage = ((int) Math.ceil((float) totalRecord / pageSize));
	int blockSize = 10; //한블럭당 보여질 페이지 수
	int currentPage=1; //현재페이지
	//아래의 코드는 아무때나하는게 아니다! 어느떄만? 누군가가 파라미터를 넘겼을 때만 
	//즉, 페이지 넘버를 클릭한 경우를 전제로 숫자화 시키는 코드이다
	if(request.getParameter("currentPage")!=null){ //즉, 페이지를 넘겼다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	//int firstPage = ((int)Math.floor((float)currentPage/blockSize)*blockSize)+1; //반복문의 시작 값
	//int lastPage = (int)Math.floor((float)currentPage/blockSize)*blockSize; //반복문의 끝 값
	int firstPage = currentPage - (currentPage-1)%blockSize; //반복문의 시작 값
	int lastPage = firstPage +(blockSize-1);  //반복문의 끝 값
	int num =totalRecord-(currentPage-1)*pageSize;//페이지당 시작 번호(힌트: 위에있는 모든 변수를 조합하면 구할 수 있음)
	int curPos = (currentPage-1)*pageSize; //페이지당 List에서의 시작 index
%>
<%="totalRecord :" + totalRecord + "<br>"%>
<%="pageSize :" + pageSize + "<br>"%>
<%="totalPage :" + totalPage + "<br>"%>
<%="blockSize :" + blockSize + "<br>"%>
<%="currentPage :" + currentPage + "<br>"%>
<%="firstPage :" + firstPage + "<br>"%>
<%="lastPage :" + lastPage + "<br>"%>
<%="num :" + num + "<br>"%>
<%="curPos :" + curPos + "<br>"%>

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
		<%if(num<1)break; %>
		<%
			//break문을 만나지 않았다는 것은 레코드가 있다는 것이므로, break문 아래에서 데이터를 추출하자 
			QnA qna = list.get(curPos++); //1page : 0~9, 2page: 10~19	
		%>
		<tr>
			<td><%=num-- %></td>
			<td>
				<%if(qna.getDepth()>0){  //답변인것만 %>
    				<img src="/images/reply2.png" style="margin-left:<%=20*qna.getDepth()%>">
    			<%} %>
    			<%=qna.getTitle() %>
    		</td>
			<td><%=qna.getWriter() %></td>
			<td><%=qna.getRegdate() %></td>
			<td><%=qna.getHit() %></td>
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