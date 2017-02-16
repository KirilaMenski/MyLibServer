<%@ include file="pattern/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="pattern/links.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<div id="main">
		<%@ include file="pattern/slide_show.jsp"%>
		<%@ include file="pattern/menu.jsp"%>

		<div id="m_book_shufle">
			<div id="all_books">Всего книг: ${numb_books}</div>
			<c:forEach items="${all_books}" var="all_books">

				<div id="b_list">
					<div id="b_img">
						<a href="view_book-id${all_books.id}"><img class="b_books_img"
							alt="books" src="ShowBooksImg?path=${all_books.image}" /></a>
					</div>
					<p id="b_description">${all_books.title}</p>
				</div>
			</c:forEach>

		</div>
		<div id="pages">
			<c:forEach items="${page}" var="page">
				<a href="books_${page}"><p class="page">${page}</p></a>
			</c:forEach>
		</div>
		<%@ include file="pattern/footer.jsp" %>
		<p id="back-top">
			<a href=""><span></span>Back to Top</a>
		</p>
	</div>
</body>
</html>