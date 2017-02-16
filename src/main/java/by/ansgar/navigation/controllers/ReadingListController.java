package by.ansgar.navigation.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.ansgar.navigation.entity.Book;
import by.ansgar.navigation.entity.ReadingList;
import by.ansgar.navigation.service.BookService;
import by.ansgar.navigation.service.ReadingListService;

@Controller
public class ReadingListController {

	private static final Logger LOG = Logger
			.getLogger(ReadingListController.class);

	@Autowired
	private ReadingListService readingService;
	@Autowired
	private BookService bookServ;

	@RequestMapping(value = "/list-{status}")
	public ModelAndView openReadingList(@PathVariable String status) {
		ModelAndView mav = new ModelAndView();
		List<ReadingList> booksInList = new ArrayList<ReadingList>();
		try {
			booksInList = readingService.allBooks(status);
		} catch (SQLException e) {
			LOG.error("Can not show books", e);
		}
		mav.addObject("books", booksInList);
		mav.setViewName("reading_list");
		return mav;
	}

	@RequestMapping(value = "/add_book_to_list")
	public @ResponseBody String addBookToList(@RequestParam String title,
			@RequestParam long book_id, @RequestParam String author,
			@RequestParam long author_id,
			@ModelAttribute ReadingList booksInList) {
		try {
			booksInList.setBook(title);
			booksInList.setBook_id(book_id);
			booksInList.setAuthor(author);
			booksInList.setAuthor_id(author_id);
			Book book = bookServ.getBookById(book_id);
			book.setImage(book.getImage());
			book.setTitle(book.getTitle());
			book.setDescription(book.getDescription());
			book.setAuthor(book.getAuthor());
			book.setAuthor_id(book.getAuthor_id());
			book.setInList(1);
			book.setRating(book.getRating());
			book.setStatus(book.getStatus());
			bookServ.editBook(book);
			System.out.println(title + book_id + author + author_id);
			bookServ.editBook(book);
			readingService.addBook(booksInList);
		} catch (SQLException e) {
			LOG.error("Can not add book!", e);
		}
		return "The book was added to list";
	}

	@RequestMapping(value = "/changeStatus")
	public @ResponseBody String doStatus(@RequestParam int id,
			@RequestParam String title, @RequestParam int book_id,
			@RequestParam String author, @RequestParam int author_id,
			@RequestParam String status, @RequestParam int pick,
			@ModelAttribute ReadingList booksInList) {
		String currentStatus = "Status changed!";

		try {
			booksInList.setId(id);
			booksInList.setPick(pick);
			booksInList.setBook(title);
			booksInList.setBook_id(book_id);
			booksInList.setAuthor(author);
			booksInList.setAuthor_id(author_id);
			readingService.editBook(booksInList);
			Book book = bookServ.getBookById(book_id);
			book.setImage(book.getImage());
			book.setTitle(book.getTitle());
			book.setDescription(book.getDescription());
			book.setAuthor(book.getAuthor());
			book.setAuthor_id(book.getAuthor_id());
			book.setInList(book.getInList());
			book.setRating(book.getRating());
			book.setStatus(pick);
			bookServ.editBook(book);
		} catch (SQLException e) {
			LOG.error("Can not change status!", e);
		}
		return currentStatus;
	}

}
