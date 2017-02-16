package by.ansgar.navigation.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.ansgar.navigation.dao.ReadingListDAO;
import by.ansgar.navigation.entity.ReadingList;

@Repository
public class ReadingListDAOImpl implements ReadingListDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBook(ReadingList book) throws SQLException {
		currentSession().save(book);

	}

	@Override
	public void editBook(ReadingList book) throws SQLException {
		currentSession().update(book);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadingList> allBooks(String status) throws SQLException {
		List<ReadingList> allBooks = new ArrayList<ReadingList>();
		switch (status) {
		case "all_book":
			allBooks = currentSession().createQuery("FROM ReadingList").list();
			break;
		case "was_read":
			allBooks = currentSession()
					.createQuery(
							"SELECT rl FROM ReadingList rl WHERE rl.pick = 1")
					.list();
			break;
		case "unread":
			allBooks = currentSession()
					.createQuery(
							"SELECT rl FROM ReadingList rl WHERE rl.pick = 0")
					.list();
			break;
		}
		return allBooks;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
