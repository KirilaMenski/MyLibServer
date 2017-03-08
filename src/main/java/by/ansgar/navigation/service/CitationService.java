package by.ansgar.navigation.service;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.Citation;

public interface CitationService {

	public void addCitation(Citation citation) throws SQLException;

	public void editCitation(Citation citation) throws SQLException;

	public void deleteCitation(Citation citation) throws SQLException;

	public List<Citation> getAllCitation() throws SQLException;

	public List<Citation> getAllCitation(int page) throws SQLException;

	public List<Citation> getLikedCitation(int page) throws SQLException;

	public List<Citation> getLastAdded() throws SQLException;

	public List<Citation> getCitationByBookId(long bookId) throws SQLException;

	public List<Citation> getCitationByBookId(long bookId, int sync) throws SQLException;

	public Citation getCitationById(long id) throws SQLException;

	public Citation getCitationByUuid(String uuid) throws SQLException;

}
