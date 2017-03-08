package by.ansgar.navigation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.ansgar.navigation.entity.Author;
import by.ansgar.navigation.entity.Book;
import by.ansgar.navigation.entity.Citation;
import by.ansgar.navigation.entity.LinkAuthorBooks;
import by.ansgar.navigation.entity.LinkBookCitations;
import by.ansgar.navigation.entity.response.AuthorResponse;
import by.ansgar.navigation.entity.response.BookResponse;
import by.ansgar.navigation.entity.response.CitationResponse;
import by.ansgar.navigation.entity.response.ServerResponse;
import by.ansgar.navigation.entity.response.User;
import by.ansgar.navigation.service.AuthorService;
import by.ansgar.navigation.service.BookService;
import by.ansgar.navigation.service.CitationService;
import by.ansgar.navigation.service.LinkAuthorBooksService;
import by.ansgar.navigation.service.LinkBookCitationsService;
import by.ansgar.navigation.util.Upload;

@RestController
public class ApiController {

	private static final String PATH = "/api/";
	private static final String DEFAULT_IMAGE = "/home/kirila/MyProgramms/BookNavigation/image/defaultImage.jpg";

	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CitationService citationService;
	@Autowired
	private LinkBookCitationsService linkBC;
	@Autowired
	private LinkAuthorBooksService linkAB;

	@RequestMapping(value = PATH + "save", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse setUserData(@RequestBody User user) {
		if (user == null)
			return new ServerResponse("User not found");
		try {
			List<AuthorResponse> authorResponse = user.getAuthors();
			for (int i = 0; i < authorResponse.size(); i++) {

				Author currentAuthor = authorService.getAuthorByUuid(authorResponse.get(i).getUuid());
				Author author = new Author();
				if (currentAuthor != null)
					author = currentAuthor;
				String authorName = authorResponse.get(i).getFirstName() + " " + authorResponse.get(i).getLastName();
				author.setUuid(authorResponse.get(i).getUuid());
				author.setFirstname(authorResponse.get(i).getFirstName());
				author.setLastname(authorResponse.get(i).getLastName());
				author.setBiography(authorResponse.get(i).getBiography());
				author.setDate(authorResponse.get(i).getDate());
				author.setHasSynchronized(1);
				author.setImage(Upload.convertAndSaveImage(authorResponse.get(i).getCoverBytes(),
						authorResponse.get(i).getFirstName() + authorResponse.get(i).getLastName(), "authors"));
				if (currentAuthor != null) {
					authorService.editAuthor(author);
				} else {
					authorService.addAuthor(author);
				}

				List<BookResponse> bookResponse = authorResponse.get(i).getBooks();
				for (int j = 0; j < bookResponse.size(); j++) {
					long authorId = authorService.getAllAuthors().get(authorService.getAllAuthors().size() - 1).getId();
					String bookTitle = bookResponse.get(j).getTitle();

					Book currentBook = bookService.getBookByUuid(bookResponse.get(j).getUuid());
					Book book = new Book();
					if (currentBook != null)
						book = currentBook;
					book.setUuid(bookResponse.get(j).getUuid());
					book.setAuthor_id(authorId);
					book.setAuthor(authorName);
					book.setTitle(bookTitle);
					book.setDescription(bookResponse.get(j).getDescription());
					book.setGenre(bookResponse.get(j).getGenre());
					book.setSeries(bookResponse.get(j).getSeries());
					book.setSeriesNum(bookResponse.get(j).getNumSeries());
					book.setInList(bookResponse.get(j).getInList());
					book.setRating(bookResponse.get(j).getRating());
					book.setHasSynchronized(1);
					book.setStatus(bookResponse.get(j).getWasRead());
					book.setImage(Upload.convertAndSaveImage(bookResponse.get(j).getCoverBytes(), bookTitle, "books"));
					if (currentBook != null) {
						bookService.editBook(book);
					} else {
						bookService.addBook(book);
					}
					addAuthorBooksLinks(authorId);
					List<CitationResponse> citationResponse = bookResponse.get(j).getCitations();
					for (int z = 0; z < citationResponse.size(); z++) {
						long bookId = bookService.getAllBook().get(bookService.getAllBook().size() - 1).getId();

						Citation currentCitation = citationService.getCitationByUuid(citationResponse.get(z).getUuid());
						Citation citation = new Citation();
						if (currentCitation != null)
							citation = currentCitation;
						citation.setUuid(citationResponse.get(z).getUuid());
						citation.setAuthor(authorName);
						citation.setAuthor_id(authorId);
						citation.setBook(bookTitle);
						citation.setBook_id(bookId);
						citation.setHasSynchronized(1);
						citation.setCitation(citationResponse.get(z).getCitation());
						citation.setDate(citationResponse.get(z).getDate());
						citation.setLiked(citationResponse.get(z).getLiked());
						if (currentCitation != null) {
							citationService.editCitation(citation);
						} else {
							citationService.addCitation(citation);
						}
						addBookCitationsLink(bookId);
					}
				}

			}
			return new ServerResponse("The data has been successfully synchronized!");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ServerResponse("Error! " + e);
		}
	}

	@RequestMapping(value = PATH + "{userId}/synchronize", method = RequestMethod.GET)
	@ResponseBody
	public User getUserData(Model model, @PathVariable long userId) throws IOException {
		return returnUserData();
	}

	private User returnUserData() throws IOException {

		User user = new User();
		user.setId(1);
		user.setFirstName("First name");
		user.setLastName("Last name");
		user.setEmail("email@tut.by");
		user.setHasSynchronized(1);
		user.setPassword("12345");
		user.setCoverBytes("");
		try {
			List<Author> allAuthors = authorService.getUnSynchAuthors();
			List<AuthorResponse> authors = new ArrayList<>();
			for (int i = 0; i < allAuthors.size(); i++) {
				Author currentAuthor = allAuthors.get(i);
				AuthorResponse author = new AuthorResponse();
				author.setId(currentAuthor.getId());
				author.setUuid(currentAuthor.getUuid());
				author.setFirstName(currentAuthor.getFirstname());
				author.setLastName(currentAuthor.getLastname());
				author.setCoverBytes(getBytesFromFile(
						new File((currentAuthor.getImage() != null) ? currentAuthor.getImage() : DEFAULT_IMAGE)));
				author.setBiography(currentAuthor.getBiography());
				author.setDate(currentAuthor.getDate());

				currentAuthor.setId(currentAuthor.getId());
				currentAuthor.setHasSynchronized(1);
				authorService.editAuthor(currentAuthor);

				List<Book> allBooks = bookService.getBookByAuthorId(author.getId(), 0);
				List<BookResponse> books = new ArrayList<>();

				for (int j = 0; j < allBooks.size(); j++) {
					Book currentBook = allBooks.get(j);
					BookResponse book = new BookResponse();
					book.setId(currentBook.getId());
					book.setUuid(currentBook.getUuid());
					book.setTitle(currentBook.getTitle());
					book.setCoverBytes(getBytesFromFile(
							new File((currentBook.getImage() != null) ? currentBook.getImage() : DEFAULT_IMAGE)));
					book.setDescription(currentBook.getDescription());
					book.setGenre(currentBook.getGenre());
					book.setInList(currentBook.getInList());
					book.setWasRead(currentBook.getStatus());
					book.setSeries(currentBook.getSeries());
					book.setNumSeries(currentBook.getSeriesNum());
					book.setRating(allBooks.get(j).getRating());

					currentBook.setId(currentBook.getId());
					currentBook.setHasSynchronized(1);
					bookService.editBook(currentBook);

					List<Citation> citationByBook = citationService.getCitationByBookId(book.getId(), 0);
					List<CitationResponse> citations = new ArrayList<>();
					for (int z = 0; z < citationByBook.size(); z++) {
						Citation currentCitation = citationByBook.get(z);
						CitationResponse citation = new CitationResponse();
						citation.setId(currentCitation.getId());
						citation.setUuid(currentCitation.getUuid());
						citation.setCitation(currentCitation.getCitation());
						citation.setLiked(currentCitation.getLiked());
						citation.setDate(currentCitation.getDate());
						citations.add(citation);

						currentCitation.setId(currentCitation.getId());
						currentCitation.setHasSynchronized(1);
						citationService.editCitation(currentCitation);
					}
					book.setCitations(citations);
					books.add(book);
				}
				author.setBooks(books);
				authors.add(author);
			}
			user.setAuthors(authors);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static String getBytesFromFile(File file) {
		InputStream is;
		byte[] bytes = null;
		String byteImage = "";
		try {
			is = new FileInputStream(file);

			long length = file.length();

			if (length > Integer.MAX_VALUE) {
				System.out.println("Too large");
			}

			bytes = new byte[(int) length];
			int offset = 0;
			int numRead = 0;

			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) > 0) {
				offset += numRead;
			}

			if (offset < bytes.length) {
				throw new IOException("Could not completely read file" + file.getName());
			}
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Base64.encodeBase64String(bytes);
	}

	private void addAuthorBooksLinks(long author_id) throws SQLException {
		LinkAuthorBooks link = new LinkAuthorBooks();
		List<Book> books = bookService.getAllBook();
		link.setAuthor(authorService.getAuthorById(author_id));
		link.setBook(bookService.getBookById(books.get(books.size() - 1).getId()));
		linkAB.addLink(link);
	}

	private void addBookCitationsLink(long book_id) throws SQLException {
		LinkBookCitations link = new LinkBookCitations();
		List<Citation> citations = citationService.getAllCitation();
		link.setBook(bookService.getBookById(book_id));
		link.setCitation(citationService.getCitationById(citations.get(citations.size() - 1).getId()));
		linkBC.addLink(link);

	}

}
