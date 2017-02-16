package by.ansgar.navigation.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ansgar.navigation.dao.LinkAuthoBooksDAO;
import by.ansgar.navigation.entity.LinkAuthorBooks;
import by.ansgar.navigation.service.LinkAuthorBooksService;

@Service
public class LinkAuthorBooksServiceImpl implements LinkAuthorBooksService {

	@Autowired
	private LinkAuthoBooksDAO linkABDAO;

	@Transactional
	public void addLink(LinkAuthorBooks linkAB) throws SQLException {
		linkABDAO.addLink(linkAB);

	}

	@Transactional
	public void editLink(LinkAuthorBooks linkAB) throws SQLException {
		linkABDAO.editLink(linkAB);

	}

	@Transactional
	public List<LinkAuthorBooks> getAllLinks() throws SQLException {
		List<LinkAuthorBooks> allLinks = linkABDAO.getAllLinks();
		return allLinks;
	}

	@Transactional
	public List<LinkAuthorBooks> getBooksByAuthorName(long authorId)
			throws SQLException {
		List<LinkAuthorBooks> booksByAuthorName = linkABDAO
				.getBooksByAuthorName(authorId);
		return booksByAuthorName;
	}

}
