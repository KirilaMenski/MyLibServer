package by.ansgar.navigation.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.ansgar.navigation.dao.LinkAuthoBooksDAO;
import by.ansgar.navigation.entity.LinkAuthorBooks;

@Repository
public class LinkAuthorBooksDAOImpl implements LinkAuthoBooksDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addLink(LinkAuthorBooks linkAB) throws SQLException {
		currentSession().save(linkAB);

	}

	@Override
	public void editLink(LinkAuthorBooks linkAB) throws SQLException {
		currentSession().update(linkAB);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkAuthorBooks> getAllLinks() throws SQLException {
		List<LinkAuthorBooks> allLinks = currentSession()
				.createQuery("FROM LinkAuthorBooks").list();
		return allLinks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkAuthorBooks> getBooksByAuthorName(long authorId)
			throws SQLException {
		List<LinkAuthorBooks> booksByName = currentSession()
				.createQuery(
						"SELECT b FROM LinkAuthorBooks lab LEFT OUTER JOIN lab.author a LEFT OUTER JOIN lab.book b"
								+ " WHERE lab.book.id = lab.book"
								+ " AND lab.author = lab.author.id"
								+ " AND lab.author.id = :author_id")
				.setParameter("author_id", authorId).list();
		return booksByName;
	}

	private Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
