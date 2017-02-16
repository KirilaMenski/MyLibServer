package by.ansgar.navigation.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ansgar.navigation.dao.ReadingListDAO;
import by.ansgar.navigation.entity.ReadingList;
import by.ansgar.navigation.service.ReadingListService;

@Service
public class ReadingListServiceImpl implements ReadingListService {

	@Autowired
	private ReadingListDAO readingDAO;

	@Transactional
	public void addBook(ReadingList book) throws SQLException {
		readingDAO.addBook(book);

	}

	@Transactional
	public void editBook(ReadingList book) throws SQLException {
		readingDAO.editBook(book);

	}

	@Transactional
	public List<ReadingList> allBooks(String status) throws SQLException {
		List<ReadingList> allBooks = readingDAO.allBooks(status);
		return allBooks;
	}

}
