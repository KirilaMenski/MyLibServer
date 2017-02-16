package by.ansgar.navigation.dao;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.ReadingList;

public interface ReadingListDAO {
	
	public void addBook (ReadingList book) throws SQLException;
	
	public void editBook (ReadingList book) throws SQLException;
	
	public List<ReadingList> allBooks (String status) throws SQLException;
	
}
