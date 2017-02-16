package by.ansgar.navigation.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.ansgar.navigation.dao.AuthorDAO;
import by.ansgar.navigation.entity.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

	public static final int MAX_RES = 20;
	private static final int LAST_AUTHOR = 9;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAuthor(Author author) throws SQLException {
		currentSession().save(author);

	}

	@Override
	public void editAuthor(Author author) throws SQLException {
		currentSession().update(author);

	}

	@Override
	public void deleteAuthors(Author author) throws SQLException {
		currentSession().delete(author);

	}

	@Override
	public List<Author> getAllAuthors() throws SQLException {
		@SuppressWarnings("unchecked")
		List<Author> allAuthors = currentSession().createQuery("FROM Author")
				.list();
		return allAuthors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAllAuthors(int page) throws SQLException {
		List<Author> authors = currentSession()
				.createQuery("SELECT a FROM Author a ORDER BY a.lastname")
				.setFirstResult(page * MAX_RES - MAX_RES).setMaxResults(MAX_RES)
				.list();
		return authors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getLastAdded() throws SQLException {
		List<Author> allAuthors = getAllAuthors();
		List<Author> lastAuthors = currentSession()
				.createQuery("FROM Author")
				.setFirstResult(allAuthors.size() - LAST_AUTHOR)
				.setMaxResults(LAST_AUTHOR).list();
		return lastAuthors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> searchAuthors(String name) throws SQLException {
		List<Author> authors = currentSession()
				.createQuery("SELECT a FROM Author a WHERE a.lastname = :name")
				.setParameter("name", name).list();
		return authors;
	}

	@Override
	public Author getAuthorById(long id) throws SQLException {
		@SuppressWarnings("unchecked")
		Author author = (Author) currentSession().get(Author.class, id);
		return author;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
