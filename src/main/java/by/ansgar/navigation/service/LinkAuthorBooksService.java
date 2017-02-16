package by.ansgar.navigation.service;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.LinkAuthorBooks;

public interface LinkAuthorBooksService {
	
public void addLink(LinkAuthorBooks linkAB) throws SQLException;
	
	public void editLink (LinkAuthorBooks linkAB) throws SQLException;
	
	public List<LinkAuthorBooks> getAllLinks () throws SQLException;
	
	public List<LinkAuthorBooks> getBooksByAuthorName (long authorId) throws SQLException;

}
