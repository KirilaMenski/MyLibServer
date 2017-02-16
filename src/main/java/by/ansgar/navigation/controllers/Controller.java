package by.ansgar.navigation.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.ansgar.navigation.entity.Author;
import by.ansgar.navigation.entity.Book;
import by.ansgar.navigation.entity.Citation;
import by.ansgar.navigation.service.AuthorService;
import by.ansgar.navigation.service.BookService;
import by.ansgar.navigation.service.CitationService;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger LOG = Logger.getLogger(Controller.class);
	@Autowired
	private AuthorService authServ;
	@Autowired
	private BookService bookServ;
	@Autowired
	private CitationService citServ;

	@RequestMapping(value = "/main")
	public ModelAndView openMainPage() {
		ModelAndView mav = new ModelAndView();
		List<Author> lastAuthors = new ArrayList<Author>();
		List<Book> lastBooks = new ArrayList<Book>();
		List<Citation> lastCit = new ArrayList<Citation>();
		try {
			lastAuthors = authServ.getLastAdded();
			lastBooks = bookServ.getLastAdded();
			lastCit = citServ.getLastAdded();
		} catch (SQLException e) {
			LOG.error("Cannot show last added!", e);
		}

		mav.addObject("lastAuthors", lastAuthors);
		mav.addObject("lastBooks", lastBooks);
		mav.addObject("lastCit", lastCit);
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, @ModelAttribute Author author, @ModelAttribute Book book) {
		ModelAndView mav = new ModelAndView();
		List<Author> founAuthors = new ArrayList<Author>();
		List<Book> foundBook = new ArrayList<Book>();
		String value = request.getParameter("search_value");
		try {
			founAuthors = authServ.searchAuthors(value);
			foundBook = bookServ.searchBook(value);
		} catch (SQLException e) {
			LOG.error("Can not found" + value, e);
		}
		System.out.println(value);
		mav.addObject("author", founAuthors);
		mav.addObject("book", foundBook);
		mav.setViewName("search");
		return mav;
	}
}
