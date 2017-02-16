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
		<%@ include file="pattern/slide_show.jsp" %>

		<%@ include file="pattern/menu.jsp"%>
		<div id="content">
			<div id="m_book_shufle">
				<div id="m_last_added_authors">
					<div class="m_inscription">Добавленные авторы</div>
					<c:forEach items="${lastAuthors}" var="lastAuthors">
						<div class="m_list">
							<div id="m_img">
								<a href="view_author-id${lastAuthors.id}"><img
									class="m_last_added_img" alt="last_added"
									src="ShowAuthorImg?path=${lastAuthors.image}" /></a>
							</div>
							<p id="m_name">${lastAuthors.firstname}
								${lastAuthors.lastname}</p>
						</div>
					</c:forEach>
				</div>

				<div class="m_last_added_books">
					<div class="m_inscription">Добавленные книги</div>
					<c:forEach items="${lastBooks}" var="lastBooks">
						<div class="m_list">
							<div id="m_img">
								<a href="view_book-id${lastBooks.id}"><img
									class="m_last_added_img" alt="last_added"
									src="ShowBooksImg?path=${lastBooks.image}" /></a>
							</div>
							<p id="m_title">${lastBooks.title}</p>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
		<%@ include file="pattern/footer.jsp" %>
		<p id="back-top">
			<a href=""><span></span>Back to Top</a>
		</p>


	</div>
</body>
</html>