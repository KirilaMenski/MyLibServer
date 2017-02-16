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
		<div id="content">
			<div id="found">
				<div id="searh_author">
					<c:forEach items="${author}" var="author">
						<a href="view_author-id${author.id}"><div id="authors_list">
								<div id="photo">
									<img alt="photo" id="author_photo"
										src="ShowAuthorImg?path=${author.image}" />
								</div>
								<p>
									<b>${author.lastname} ${author.firstname}</b>
								</p>
								<p id="author_biogr">${author.biography}</p>

							</div>
					</c:forEach>
				</div>
				<div id="search_book">
					<c:forEach items="${book}" var="book">
						<a href="view_book-id${book.id}"><div id="books_list">
								<div id="photo">
									<img alt="photo" id="author_photo"
										src="ShowBooksImg?path=${book.image}" />
								</div>
								<p>
									<b>${book.title}</b>
								</p>
								<p id="book_description">${book.description}</p>

							</div>
					</c:forEach>
				</div>
				<div id="search_citation">
					<c:forEach items="${citation}" var="author"></c:forEach>
				</div>
			</div>
		</div>
		<%@ include file="pattern/footer.jsp" %>
	</div>

</body>
</html>