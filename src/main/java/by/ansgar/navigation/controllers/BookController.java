package by.ansgar.navigation.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import by.ansgar.navigation.dao.impl.BookDAOImpl;
import by.ansgar.navigation.entity.Book;
import by.ansgar.navigation.entity.Citation;
import by.ansgar.navigation.entity.LinkBookCitations;
import by.ansgar.navigation.service.BookService;
import by.ansgar.navigation.service.CitationService;
import by.ansgar.navigation.service.LinkBookCitationsService;
import by.ansgar.navigation.util.DateCalendar;
import by.ansgar.navigation.util.Upload;

@Controller
public class BookController {

	private static final Logger LOG = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookService;
	@Autowired
	private LinkBookCitationsService linkBC;
	@Autowired
	private CitationService citationService;

	private long book_id;;

	@RequestMapping(value = "/books_{page}")
	public ModelAndView openBooksPage(@PathVariable int page) {
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = new ArrayList<Book>();
		List<Book> booksOnPage = new ArrayList<Book>();
		List<Integer> pages = new ArrayList<Integer>();

		try {
			allBooks = bookService.getAllBook();

			if (page == 0) {
				page = 1;
			}
			booksOnPage = bookService.getAllBook(page);
			for (int j = 0; j < Math.ceil((double) allBooks.size()
					/ (double) BookDAOImpl.MAX_RES); j++) {
				pages.add(j + 1);
				System.out.println(pages);
			}
		} catch (SQLException e) {
			LOG.error("Cannot display all books!", e);
		}
		mav.addObject("numb_books", allBooks.size());
		mav.addObject("all_books", booksOnPage);
		mav.addObject("page", pages);
		mav.setViewName("books");
		return mav;
	}

	@RequestMapping(value = "/view_book-id{id}")
	public ModelAndView getBooks(@PathVariable long id,
			@ModelAttribute LinkBookCitations link, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Book book = null;
		List<LinkBookCitations> bookCit = new ArrayList<LinkBookCitations>();
		book_id = id;
		try {
			book = bookService.getBookById(id);
			bookCit = linkBC.getCitationFromBook(id);
		} catch (SQLException e) {
			LOG.error("Cannot show book!", e);
		}
		mav.addObject("book", book);
		mav.addObject("bookCitations", bookCit);
		mav.setViewName("view_book");
		return mav;
	}

	@RequestMapping(value = "/add_citation")
	public ModelAndView addCitation(@ModelAttribute Citation citation,
			BindingResult result) {
		ModelAndView mav = new ModelAndView();
		try {
			citation.setDate(DateCalendar.day + "." + DateCalendar.month + "."
					+ DateCalendar.year + ":" + DateCalendar.hour + "."
					+ DateCalendar.min);
			citationService.addCitation(citation);
			addBookCitationsLink();
		} catch (SQLException e) {
			LOG.error("Cannot add citation!", e);
		}
		mav.setViewName("redirect:/view_book-id" + book_id);
		return mav;
	}

	@RequestMapping(value = "/update_book-{id}", headers = "content-type=multipart/*", method = {
			RequestMethod.POST })
	public ModelAndView editBook(@PathVariable long id,
			@ModelAttribute Book book,
			@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		Upload.doUpload(multipartFile, "books");

		try {
			book.setId(id);
			book.setImage(Upload.path.toString());
			bookService.editBook(book);
		} catch (SQLException e) {
			LOG.error("Cannot update the book!", e);
		}
		mav.setViewName("redirect:/view_book-id" + book_id);
		return mav;
	}

	@RequestMapping(value = "/delete_book-{id}")
	public ModelAndView deleteBook(@PathVariable long id,
			@ModelAttribute Book book) {
		ModelAndView mav = new ModelAndView();
		book = new Book();
		book.setId(id);
		try {
			bookService.deleteBook(book);
		} catch (SQLException e) {
			LOG.error("Cannot delete book!", e);
		}
		mav.setViewName("book");
		return mav;
	}

	@RequestMapping(value = "/like_book")
	public @ResponseBody String likeBook(@RequestParam int rating,
			@RequestParam long id) {
		try {
			Book book = bookService.getBookById(id);
			book.setAuthor(book.getAuthor());
			book.setAuthor_id(book.getAuthor_id());
			book.setDescription(book.getDescription());
			book.setImage(book.getImage());
			book.setInList(book.getInList());
			book.setStatus(book.getStatus());
			book.setTitle(book.getTitle());
			book.setRating(rating);
			bookService.editBook(book);
		} catch (SQLException e) {
			LOG.error("Can not like book!", e);
		}
		System.out.println(rating);
		return ""+rating;
	}

	private void addBookCitationsLink() throws SQLException {
		LinkBookCitations link = new LinkBookCitations();
		List<Citation> citations = citationService.getAllCitation();
		link.setBook(bookService.getBookById(book_id));
		link.setCitation(citationService
				.getCitationById(citations.get(citations.size() - 1).getId()));
		linkBC.addLink(link);

	}

	@ModelAttribute
	public Citation citation() {
		return new Citation();
	}

}
