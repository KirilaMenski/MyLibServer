package by.ansgar.navigation.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ansgar.navigation.dao.CitationDAO;
import by.ansgar.navigation.entity.Citation;
import by.ansgar.navigation.service.CitationService;

@Service
public class CitationServiceImpl implements CitationService {

	@Autowired
	private CitationDAO citationDAO;

	@Transactional
	public void addCitation(Citation citation) throws SQLException {
		citationDAO.addCitation(citation);

	}

	@Transactional
	public void editCitation(Citation citation) throws SQLException {
		citationDAO.editCitation(citation);

	}

	@Transactional
	public void deleteCitation(Citation citation) throws SQLException {
		citationDAO.deleteCitation(citation);

	}

	@Transactional
	public List<Citation> getAllCitation() throws SQLException {
		List<Citation> allCitation = citationDAO.getAllCitation();
		return allCitation;
	}

	@Transactional
	public Citation getCitationById(long id) throws SQLException {
		Citation citation = citationDAO.getCitationById(id);
		return citation;
	}

	@Transactional
	public List<Citation> getAllCitation(int page) throws SQLException {
		List<Citation> allCitation = citationDAO.getAllCitation(page);
		return allCitation;
	}
	
	@Transactional
	public List<Citation> getLikedCitation(int page) throws SQLException {
		List<Citation> allCitation = citationDAO.getLikedCitation(page);
		return allCitation;
	}

	@Transactional
	public List<Citation> getLastAdded() throws SQLException {
		List<Citation> lastCitations = citationDAO.getLastAdded();
		return lastCitations;
	}

}
