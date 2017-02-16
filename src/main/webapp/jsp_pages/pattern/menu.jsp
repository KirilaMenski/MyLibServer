<div class="menu">
	<div id="menu_block">
		<ul>
			<li><a href="main"><spring:message code="label.main" /></a></li>
			<li><a href="authors_1"><spring:message code="label.authors" /></a></li>
			<li><a href="books_1"><spring:message code="label.books" /></a></li>
			<li><a href="citations_1_all"><spring:message
						code="label.citation" /></a>
				<div id="drop_menu">
					<ul>
						<a href="citations_1_all"><li>All citations</li></a>
						<a href="citations_1_liked"><li>Liked citations</li></a>
					</ul>
				</div></li>
			<li><a href="list-all_book"><spring:message
						code="label.reading_book" /></a>
				<div id="drop_menu">
					<ul>
						<a href="list-all_book"><li>All</li></a>
						<a href="list-was_read"><li>Was Reading</li></a>
						<a href="list-unread"><li>Unreading</li></a>
					</ul>
				</div></li>
		</ul>
		<div id="search">
			<form action="search" method="POST">
				<input type="text" name="search_value"
					placeholder="Input what are you search" /> <input type="submit"
					value="Search" />
			</form>
		</div>
	</div>
</div>
