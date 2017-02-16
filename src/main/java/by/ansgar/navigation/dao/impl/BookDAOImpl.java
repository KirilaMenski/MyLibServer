package by.ansgar.navigation.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.ansgar.navigation.dao.BookDAO;
import by.ansgar.navigation.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	public static final int MAX_RES = 98;
	private static final int LAST_BOOKS = 9;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBook(Book book) throws SQLException {
		currentSession().save(book);

	}

	@Override
	public void editBook(Book book) throws SQLException {
		currentSession().update(book);

	}

	@Override
	public void deleteBook(Book book) throws SQLException {
		currentSession().delete(book);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Book getBookById(long id) throws SQLException {
		Book book = (Book) currentSession().get(Book.class, id);
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBook() throws SQLException {
		List<Book> allBook = currentSession().createQuery("FROM Book").list();
		return allBook;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBook(int page) throws SQLException {
		List<Book> allBook = currentSession().createQuery("SELECT b FROM Book b ORDER BY b.title")
				.setFirstResult(page * MAX_RES - MAX_RES).setMaxResults(MAX_RES).list();
		return allBook;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getLastAdded() throws SQLException {
		List<Book> allBooks = getAllBook();
		List<Book> lastBooks = currentSession().createQuery("FROM Book")
				.setFirstResult(allBooks.size() - LAST_BOOKS).setMaxResults(LAST_BOOKS).list();
		return lastBooks;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchBook(String title) throws SQLException {
		List<Book> book = currentSession().createQuery("SELECT b FROM Book b WHERE b.title =:title")
				.setParameter("title", title).list();
		return book;
	}

}
