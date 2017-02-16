package by.ansgar.navigation.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.ansgar.navigation.dao.LinkBookCitationsDAO;
import by.ansgar.navigation.entity.LinkBookCitations;

@Repository
public class LinkBookCitationsDAOImpl implements LinkBookCitationsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addLink(LinkBookCitations linkBC) throws SQLException {
		currentSession().save(linkBC);

	}

	@Override
	public void editLink(LinkBookCitations linkBC) throws SQLException {
		currentSession().update(linkBC);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkBookCitations> getCitationFromBook(long id)
			throws SQLException {
		List<LinkBookCitations> citations = currentSession()
				.createQuery(
						"SELECT c FROM LinkBookCitations lbc LEFT OUTER JOIN lbc.book b LEFT OUTER JOIN lbc.citation c"
								+ " WHERE lbc.citation.id = lbc.citation"
								+ " AND lbc.book = lbc.book.id"
								+ " AND lbc.book.id = :book_id")
				.setParameter("book_id", id).list();
		return citations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkBookCitations> getAllLinks() throws SQLException {
		List<LinkBookCitations> allLinks = currentSession()
				.createQuery("FROM LinkBookCitations").list();
		return allLinks;
	}

	private Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
