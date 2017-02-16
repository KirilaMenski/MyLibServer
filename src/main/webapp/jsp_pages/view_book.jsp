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
				<form:form action="add_citation" method="post"
					commandName="citation">
					<form:textarea type="text" path="citation"
						placeholder="description" />
					<br />
					<form:input type="hidden" path="author" value="${book.author}" />
					<form:input type="hidden" path="author_id"
						value="${book.author_id}" />
					<form:input type="hidden" path="book" value="${book.title}" />
					<form:input type="hidden" path="book_id" value="${book.id}" />
					<input type="submit" id="submit" value="Add" />
				</form:form>
			</div>
			<p id="add">
				<a href="">Добавить</a>
			</p>
			<div id="v_b_rating">
				<h2><p>Rating:<span id="v_b_rat"> ${book.rating}</span></p></h2>
				<div id="star1" onclick="likeBook('${book.id}', '1');"></div>
				<div id="star2" onclick="likeBook('${book.id}', '2');"></div>
				<div id="star3" onclick="likeBook('${book.id}', '3');"></div>
				<div id="star4" onclick="likeBook('${book.id}', '4');"></div>
				<div id="star5" onclick="likeBook('${book.id}', '5');"></div>
			</div>
			<br />

			<div id="content_list">


				<div id="book_inf">
					<div id="cover_book">
						<img id="book_img" alt="author_img"
							src="ShowBooksImg?path=${book.image}" /> <input type="hidden"
							id="input_book_title" value="${book.title}" /> <input
							type="hidden" id="input_book_id" value="${book.id}" /> <input
							type="hidden" id="input_author_name" value="${book.author}" /> <input
							type="hidden" id="input_author_id" value="${book.author_id}" />
						<div id="v_b_status">
							<c:if test="${book.status eq 1}">
								<div id="v_b_status_img"></div>
							</c:if>
						</div>
						<c:if test="${book.status eq 0 && book.inList == 0}">
							<p id="add_book_to_list" onclick="addBookToList();"></p>
						</c:if>
						<c:if test="${book.inList eq 1}">
							<a href="list-all_book"><p id="v_b_in_list">В списке</p></a>
						</c:if>
						<p id="v_b_added"></p>
					</div>
					<input type="hidden" id="v_b_book_path" value="${book.image}" /> <input
						type="hidden" id="v_b_book_status" value="${book.status}" /> <input
						type="hidden" id="v_b_book_description"
						value="${book.description}" />
					<p id="book_title">${book.title}</p>
					<a href="view_author-id${book.author_id}"><p
							id="v_b_book_author">${book.author}</p></a>
					<p id="update" onclick="showUpdate(${book.id});">
						<spring:message code="label.update" />
					</p>
					<ul>

						<li><div id="update${book.id}">
								<p id="close_update" onclick="closeUpdate(${book.id});"></p>
								<br />
								<form:form action="update_book-${book.id}" method="POST"
									commandName="book" enctype="multipart/form-data">
									<input type="file" name="file" value="${book.image}" />
									<form:input type="text" path="author" value="${book.author}"
										placeholder="author" />
									<form:input type="text" path="author_id"
										value="${book.author_id}" placeholder="author_id" />
									<form:input type="hidden" path="status" value="${book.status}"
										placeholder="status" />
									<form:input type="hidden" path="inList" value="${book.inList}"
										placeholder="inList" />
									<form:input path="title" placeholder="title"
										value="${book.title}" />
									<form:textarea placeholder="Description" path="description"
										cols="30" rows="10" value="${book.description}" />
									<input type="submit" value="Update" />
								</form:form>
							</div></li>
					</ul>
					<br /> <br />
					<p id="book_description">${book.description}</p>
				</div>


			</div>
			<br /> <br /> <br />
			<div id="denotation">Цитаты:</div>
			<br />
			<c:forEach items="${bookCitations}" var="bookCitations">
				<div id="v_b_cit">
					<c:if test="${bookCitations.liked eq 1}">
						<div id="c_liked">
							<img alt="liked" src="jsp_pages/res/utility/liked.png">
						</div>
					</c:if>
					"${bookCitations.citation}" ${bookCitations.date}
					<c:if test="${bookCitations.liked eq 0}">
						<p>
							<img alt="do_liked" src="jsp_pages/res/utility/do_liked.png"
								onclick="doLiked('${bookCitations.id}')"
								style="cursor: pointer;" />
						</p>
					</c:if>
					<p id="c_status${bookCitations.id}" style="color: green;"></p>
				</div>
			</c:forEach>

		</div>
		<%@ include file="pattern/footer.jsp"%>
		<p id="back-top">
			<a href=""><span></span>Back to Top</a>
		</p>
	</div>
</body>
</html>