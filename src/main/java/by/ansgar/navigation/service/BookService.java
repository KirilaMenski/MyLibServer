package by.ansgar.navigation.service;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.Book;

public interface BookService {

	public void addBook(Book book) throws SQLException;

	public void editBook(Book book) throws SQLException;

	public void deleteBook(Book book) throws SQLException;

	public Book getBookById(long id) throws SQLException;

	public List<Book> getAllBook() throws SQLException;
	
	public List<Book> getBookByAuthorId(long authorId) throws SQLException;
	
	public List<Book> getBookByAuthorId(long authorId, int sync) throws SQLException;
	
	public List<Book> getAllBook(int page) throws SQLException;
	
	public List<Book> getLastAdded () throws SQLException;
	
	public List<Book> searchBook(String title) throws SQLException;
	
}
