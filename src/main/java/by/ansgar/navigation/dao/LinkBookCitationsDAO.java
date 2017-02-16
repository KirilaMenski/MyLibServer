package by.ansgar.navigation.dao;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.LinkBookCitations;

public interface LinkBookCitationsDAO {
	
	public void addLink (LinkBookCitations linkBC) throws SQLException;
	
	public void editLink (LinkBookCitations linkBC) throws SQLException;
	
	public List<LinkBookCitations> getCitationFromBook (long id) throws SQLException;
	
	public List<LinkBookCitations> getAllLinks () throws SQLException;

}
