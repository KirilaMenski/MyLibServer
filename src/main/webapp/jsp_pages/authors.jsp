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
				<form:form action="add_author" method="post" commandName="author"
					enctype="multipart/form-data">
					<input type="file" name="file" />
					<form:input type="text" path="firstname" width="150"
						placeholder="firstname" />
					<form:input type="text" path="lastname" width="150"
						placeholder="lastname" />
					<br />
					<form:textarea type="text" path="biography" placeholder="biography" />
					<br />
					<input type="submit" id="submit" value="Add" />
				</form:form>
			</div>
			<p id="add">
				<a href="">Добавить</a>
			</p>
			<c:forEach items="${all_authors}" var="all_authors">
				<a href="view_author-id${all_authors.id}"><div id="authors_list">
						<div id="photo">
							<img alt="photo" id="author_photo"
								src="ShowAuthorImg?path=${all_authors.image}" />
						</div>
						<div id="delete">
							<p id="file_delete"></p>
						</div>
						<p>
							<b>${all_authors.lastname} ${all_authors.firstname}</b>
						</p>
						<p id="author_biogr">${all_authors.biography}</p>

					</div>
			</c:forEach>
		</div>
		<div id="pages">
			<c:forEach items="${page}" var="page">
				<a href="authors_${page}"><p>${page}</p></a>
			</c:forEach>
		</div>
		<%@ include file="pattern/footer.jsp" %>
		<p id="back-top">
			<a href=""><span></span>Back to Top</a>
		</p>
	</div>
</body>
</html>