package by.ansgar.navigation.dao;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.Book;

public interface BookDAO {

	public void addBook(Book book) throws SQLException;

	public void editBook(Book book) throws SQLException;

	public void deleteBook(Book book) throws SQLException;

	public Book getBookById(long id) throws SQLException;

	public List<Book> getAllBook() throws SQLException;
	
	public List<Book> getAllBook(int page) throws SQLException;
	
	public List<Book> getLastAdded () throws SQLException;
	
	public List<Book> searchBook(String title) throws SQLException;

}
