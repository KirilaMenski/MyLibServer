package by.ansgar.navigation.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.ansgar.navigation.dao.CitationDAO;
import by.ansgar.navigation.entity.Citation;

@Repository
public class CitationDAOImpl implements CitationDAO {

	public static final int MAX_RES = 100;
	private static final int LAST_CIT = 9;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addCitation(Citation citation) throws SQLException {
		currentSession().save(citation);

	}

	@Override
	public void editCitation(Citation citation) throws SQLException {
		currentSession().update(citation);

	}

	@Override
	public void deleteCitation(Citation citation) throws SQLException {
		currentSession().delete(citation);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Citation> getAllCitation() throws SQLException {
		List<Citation> allCitation = currentSession()
				.createQuery("FROM Citation").list();
		return allCitation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Citation> getAllCitation(int page) throws SQLException {
		List<Citation> allCitation = currentSession()
				.createQuery("FROM Citation")
				.setFirstResult(page * MAX_RES - MAX_RES).setMaxResults(MAX_RES)
				.list();
		return allCitation;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Citation> getLikedCitation(int page) throws SQLException {
		List<Citation> allCitation = currentSession()
				.createQuery("FROM Citation WHERE liked = 1")
				.setFirstResult(page * MAX_RES - MAX_RES).setMaxResults(MAX_RES)
				.list();
		return allCitation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Citation> getLastAdded() throws SQLException {
		List<Citation> allCitation = getAllCitation();
		List<Citation> lastCitation = currentSession()
				.createQuery("FROM Citation WHERE liked = 1")
				.setFirstResult(allCitation.size() - LAST_CIT)
				.setMaxResults(LAST_CIT).list();
		return lastCitation;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

	@Override
	public Citation getCitationById(long id) throws SQLException {
		Citation citation = (Citation) currentSession().get(Citation.class, id);
		return citation;
	}

}
