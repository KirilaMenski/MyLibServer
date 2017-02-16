package by.ansgar.navigation.dao;

import java.sql.SQLException;
import java.util.List;

import by.ansgar.navigation.entity.Author;

public interface AuthorDAO {

	public void addAuthor(Author author) throws SQLException;
	
	public void editAuthor(Author author) throws SQLException;
	
	public void deleteAuthors(Author author) throws SQLException;
	
	public List<Author> getAllAuthors () throws SQLException;
	
	public List<Author> getAllAuthors (int page) throws SQLException;
	
	public List<Author> getLastAdded () throws SQLException;
	
	public List<Author> searchAuthors(String name) throws SQLException;
	
	public Author getAuthorById(long id) throws SQLException;
	
}
