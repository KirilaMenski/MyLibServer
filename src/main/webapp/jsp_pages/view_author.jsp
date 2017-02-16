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
			<div id="add_display">
				<form:form action="add_book" method="post" commandName="book"
					enctype="multipart/form-data">
					<input type="file" name="file" />
					<form:input type="text" path="title" width="150"
						placeholder="title" />
					<br />
					<form:textarea type="text" path="description"
						placeholder="description" />
					<br />
					<form:input type="hidden" path="author"
						value="${author.firstname} ${author.lastname}" />
					<form:input type="hidden" path="author_id" value="${author.id}" />
					<input type="submit" id="submit" value="Add" />
				</form:form>
			</div>
			<p id="add">
				<a href="">Добавить</a>
			</p>
			<div id="content_list">
				<div id="author_inf">
					<div id="author_image">
						<img id="author_img" alt="author_img"
							src="ShowAuthorImg?path=${author.image}" />
					</div>
					<p id="author_name">${author.firstname} ${author.lastname}</p>
					<p id="update" onclick="showUpdate(${author.id});">
						<spring:message code="label.update" />
					</p>
					<ul>

						<li><div id="update${author.id}">
								<p id="close_update" onclick="closeUpdate(${author.id});"></p>
								<br />
								<form:form action="update_author-${author.id}" method="POST"
									commandName="author" enctype="multipart/form-data">
									<input type="file" name="file" />
									<form:input type="text" path="firstname"
										value="${author.firstname}" />
									<form:input type="text" path="lastname"
										value="${author.lastname}" />
									<form:textarea placeholder="Biography" path="biography"
										cols="30" rows="10" value="${author.biography}" />
									<input type="submit" value="Update" />
								</form:form>
							</div></li>
					</ul>
					<br /> <br />
					<p id="author_biogr">${author.biography}</p>
				</div>
			</div>
			<br /> <br /> <br />
			<div id="denotation">Список книг:</div>
			<br />

			<c:forEach items="${author_books}" var="author_books">
				<a href="view_book-id${author_books.id}"><div id="author_books">
						<div id="book_image">
							<img class="author_books_img" alt="books"
								src="ShowBooksImg?path=${author_books.image}" />
						</div>
						<p id="book_title">${author_books.title}</p>
						<p id="book_description">${author_books.description}</p>
					</div></a>
			</c:forEach>

		</div>
		<%@ include file="pattern/footer.jsp" %>
		<p id="back-top">
			<a href=""><span></span>Back to Top</a>
		</p>
	</div>
</body>
</html>