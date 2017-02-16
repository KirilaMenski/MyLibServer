package by.ansgar.navigation.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ansgar.navigation.dao.AuthorDAO;
import by.ansgar.navigation.entity.Author;
import by.ansgar.navigation.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorDAO authorDAO;
	
	@Transactional
	public void addAuthor(Author author) throws SQLException {
		authorDAO.addAuthor(author);
		
	}

	@Transactional
	public void editAuthor(Author author) throws SQLException {
		authorDAO.editAuthor(author);
		
	}

	@Transactional
	public void deleteAuthors(Author author) throws SQLException {
		authorDAO.deleteAuthors(author);
		
	}

	@Transactional
	public List<Author> getAllAuthors() throws SQLException {
		List<Author> allAuthors = authorDAO.getAllAuthors(); 
		return allAuthors;
	}

	@Transactional
	public Author getAuthorById(long id) throws SQLException {
		Author author = (Author) authorDAO.getAuthorById(id);
		return author;
	}

	@Transactional
	public List<Author> getAllAuthors(int page) throws SQLException {
		List<Author> authors = authorDAO.getAllAuthors(page);
		return authors;
	}
	
	@Transactional
	public List<Author> searchAuthors(String name) throws SQLException {
		List<Author> authors = authorDAO.searchAuthors(name);
		return authors;
	}

	@Transactional
	public List<Author> getLastAdded() throws SQLException {
		List<Author> lastAuthor = authorDAO.getLastAdded();
		return lastAuthor;
	}

	

}
