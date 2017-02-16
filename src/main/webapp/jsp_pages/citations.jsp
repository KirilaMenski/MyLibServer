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
			<c:forEach items="${all_citations}" var="all_citations">
				<div id="v_b_cit">
					<c:if test="${all_citations.liked eq 1}">
						<div id="c_liked">
							<img alt="liked" src="jsp_pages/res/utility/liked.png">
						</div>
					</c:if>
					<p>"${all_citations.citation}"</p>
					<div id="c_cit_inform">
						<b><a href="view_author-id${all_citations.author_id}">${all_citations.author}</a>,
							<a href="view_book-id${all_citations.book_id}">${all_citations.book}</a></b>
					</div>
					<c:if test="${all_citations.liked eq 0}">
						<img alt="do_liked" src="jsp_pages/res/utility/do_liked.png"
							onclick="doLiked('${all_citations.id}')" style="cursor: pointer;">
					</c:if>
					<p id='c_status${all_citations.id}' style="color: green;"></p>

				</div>
			</c:forEach>
		</div>
		<div id="pages">
			<c:forEach items="${page}" var="page">
				<a href="citations_${page}_${cit}"><p class="page">${page}</p></a>
			</c:forEach>
		</div>
		<%@ include file="pattern/footer.jsp" %>
		<p id="back-top">
			<a href=""><span></span>Back to Top</a>
		</p>
	</div>
</body>
</html>