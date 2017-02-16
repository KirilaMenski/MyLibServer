package by.ansgar.navigation.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ansgar.navigation.dao.BookDAO;
import by.ansgar.navigation.entity.Book;
import by.ansgar.navigation.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Transactional
	public void addBook(Book book) throws SQLException {
		bookDAO.addBook(book);

	}

	@Transactional
	public void editBook(Book book) throws SQLException {
		bookDAO.editBook(book);

	}

	@Transactional
	public void deleteBook(Book book) throws SQLException {
		bookDAO.deleteBook(book);

	}

	@Transactional
	public Book getBookById(long id) throws SQLException {
		Book book = bookDAO.getBookById(id);
		return book;
	}

	@Transactional
	public List<Book> getAllBook() throws SQLException {
		List<Book> allBook = bookDAO.getAllBook();
		return allBook;
	}

	@Transactional
	public List<Book> searchBook(String title) throws SQLException {
		List<Book> book = bookDAO.searchBook(title);
		return book;
	}

	
	@Transactional
	public List<Book> getAllBook(int page) throws SQLException {
		List<Book> allBook = bookDAO.getAllBook(page);
		return allBook;
	}

	@Transactional
	public List<Book> getLastAdded() throws SQLException {
		List<Book> lastBooks = bookDAO.getLastAdded();
		return lastBooks;
	}

	
}
