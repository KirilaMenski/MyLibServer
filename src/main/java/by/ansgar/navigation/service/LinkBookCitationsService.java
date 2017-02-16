package by.ansgar.navigation.service;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.LinkBookCitations;

public interface LinkBookCitationsService {
	
public void addLink (LinkBookCitations linkBC) throws SQLException;
	
	public void editLink (LinkBookCitations linkBC) throws SQLException;
	
	public List<LinkBookCitations> getCitationFromBook (long id) throws SQLException;
	
	public List<LinkBookCitations> getAllLinks () throws SQLException;

}
