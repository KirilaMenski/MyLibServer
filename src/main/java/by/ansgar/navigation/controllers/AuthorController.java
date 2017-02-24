package by.ansgar.navigation.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import by.ansgar.navigation.dao.impl.AuthorDAOImpl;
import by.ansgar.navigation.entity.Author;
import by.ansgar.navigation.entity.Book;
import by.ansgar.navigation.entity.LinkAuthorBooks;
import by.ansgar.navigation.service.AuthorService;
import by.ansgar.navigation.service.BookService;
import by.ansgar.navigation.service.LinkAuthorBooksService;
import by.ansgar.navigation.util.DateCalendar;
import by.ansgar.navigation.util.Upload;

@Controller
public class AuthorController {
	private static final Logger LOG = Logger.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorServ;
	@Autowired
	private LinkAuthorBooksService linkAB;
	@Autowired
	private BookService bookSer;
	private long author_id;

	@RequestMapping(value = "/authors_{page}")
	public ModelAndView openAuthorsPage(@PathVariable int page) {
		ModelAndView mav = new ModelAndView();
		List<Author> allAuthors = new ArrayList<Author>();
		List<Author> authorsOnPage = new ArrayList<Author>();
		List<Integer> pages = new ArrayList<Integer>();

		try {
			allAuthors = authorServ.getAllAuthors();

			if (page == 0) {
				page = 1;
			}
			authorsOnPage = authorServ.getAllAuthors(page);
			for (int j = 0; j < Math.ceil((double) allAuthors.size() / (double) AuthorDAOImpl.MAX_RES); j++) {
				pages.add(j + 1);
			}

		} catch (SQLException e) {
			LOG.error("Cannot display all authors", e);
		}
		mav.setViewName("authors");
		mav.addObject("all_authors", authorsOnPage);
		mav.addObject("page", pages);
		return mav;
	}

	@RequestMapping(value = "/add_author", headers = "content-type=multipart/*", method = { RequestMethod.POST })
	public ModelAndView addAuthor(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			@ModelAttribute Author author, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Upload.doUpload(multipartFile, "authors");
		try {
			author.setImage(Upload.path.toString());
			authorServ.addAuthor(author);
		} catch (SQLException e) {
			LOG.error("Cannot add author!", e);
		}
		mav.setViewName("redirect:/authors_1");
		return mav;
	}

	@RequestMapping(value = "/view_author-id{id}")
	public ModelAndView viewAuthor(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Author author = null;
		List<LinkAuthorBooks> authorBooks = new ArrayList<LinkAuthorBooks>();
		author_id = id;
		try {
			author = authorServ.getAuthorById(id);
			authorBooks = linkAB.getBooksByAuthorName(id);
		} catch (SQLException e) {
			LOG.error("Cannot view author by name", e);
		}
		mav.addObject("author_books", authorBooks);
		mav.addObject("author", author);
		mav.setViewName("view_author");
		return mav;
	}

	@RequestMapping(value = "/add_book", headers = "content-type=multipart/*")
	public ModelAndView addBook(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			@ModelAttribute Book book, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Upload.doUpload(multipartFile, "books");
		try {
			book.setImage(Upload.path.toString());
			bookSer.addBook(book);
			addAuthorBooksLinks();
		} catch (SQLException e) {
			LOG.error("Cannot add book!", e);
		}
		mav.setViewName("forward:/view_author-id" + author_id);
		return mav;
	}

	@RequestMapping(value = "/delete_author-{id}")
	public ModelAndView deleteAuthor(@PathVariable long id, @ModelAttribute Author author) {
		ModelAndView mav = new ModelAndView();
		author = new Author();
		author.setId(id);
		try {
			authorServ.deleteAuthors(author);
		} catch (SQLException e) {
			LOG.error("Cannot delete author", e);
		}
		mav.setViewName("redirect:/authors_1");
		return mav;
	}

	@RequestMapping(value = "/update_author-{id}", headers = "content-type=multipart/*", method = {
			RequestMethod.POST })
	public ModelAndView updateAuthor(@PathVariable long id, @ModelAttribute Author author,
			@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		Upload.doUpload(multipartFile, "authors");
		try {
			author.setId(id);
			author.setImage(Upload.path.toString());
			authorServ.editAuthor(author);
		} catch (SQLException e) {
			LOG.error("Cannot update author", e);
		}
		mav.setViewName("redirect:/view_author-id" + author_id);
		return mav;
	}

	private void addAuthorBooksLinks() throws SQLException {
		LinkAuthorBooks link = new LinkAuthorBooks();
		List<Book> books = bookSer.getAllBook();
		link.setAuthor(authorServ.getAuthorById(author_id));
		link.setBook(bookSer.getBookById(books.get(books.size() - 1).getId()));
		linkAB.addLink(link);
	}

	@ModelAttribute
	public Author author() {
		return new Author();
	}

	@ModelAttribute
	public Book book() {
		return new Book();
	}
}
