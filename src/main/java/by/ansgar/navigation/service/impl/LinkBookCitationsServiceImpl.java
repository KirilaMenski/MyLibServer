package by.ansgar.navigation.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ansgar.navigation.dao.LinkBookCitationsDAO;
import by.ansgar.navigation.entity.LinkBookCitations;
import by.ansgar.navigation.service.LinkBookCitationsService;

@Service
public class LinkBookCitationsServiceImpl implements LinkBookCitationsService {

	@Autowired
	private LinkBookCitationsDAO linkBCDAO;
	
	@Transactional
	public void addLink(LinkBookCitations linkBC) throws SQLException {
		linkBCDAO.addLink(linkBC);

	}

	@Transactional
	public void editLink(LinkBookCitations linkBC) throws SQLException {
		linkBCDAO.editLink(linkBC);

	}

	@Transactional
	public List<LinkBookCitations> getCitationFromBook(long id)
			throws SQLException {
		List<LinkBookCitations> citations = linkBCDAO.getCitationFromBook(id);
		return citations;
	}

	@Transactional
	public List<LinkBookCitations> getAllLinks() throws SQLException {
		List<LinkBookCitations> allLinks = linkBCDAO.getAllLinks();
		return allLinks;
	}

}
