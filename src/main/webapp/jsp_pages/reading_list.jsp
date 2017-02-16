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
			<div id="reading_list">
				<h1>Текущий список:</h1>
				<br />
				<ul>
					<c:forEach items="${books}" var="books">
						<li><div id="r_l_book_author">
								<a href="view_book-id${books.book_id}">${books.book}</a>, <a
									href="view_author-id${books.author_id}">${books.author}</a>
							</div>
							<p id="r_l_status${books.id}" style="color: green"></p>
							<div id="r_l_read">
								<input type="hidden" id="r_l_input_id${books.id}"
									value="${books.id}" /> <input type="hidden"
									id="r_l_input_book_title${books.id}" value="${books.book}" />
								<input type="hidden" id="r_l_input_book_id${books.id}"
									value="${books.book_id}" /> <input type="hidden"
									id="r_l_input_author_name${books.id}" value="${books.author}" />
								<input type="hidden" id="r_l_input_author_id${books.id}"
									value="${books.author_id}" />
								<c:if test="${books.pick eq 1}">
									<img id="r_l_img" alt="was_read"
										src="jsp_pages/res/utility/accept.png"
										onclick="changeStatus('un_read', '0', '${books.id}');" />
								</c:if>
								<c:if test="${books.pick eq 0}">
									<img id="r_l_img" alt="un_read"
										src="jsp_pages/res/utility/denied.png"
										onclick="changeStatus('was_read', '1','${books.id}');" />
								</c:if>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<%@ include file="pattern/footer.jsp"%>
	</div>
</body>
</html>