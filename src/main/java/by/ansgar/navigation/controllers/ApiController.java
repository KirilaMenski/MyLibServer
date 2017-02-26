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
		if(user == null) return new ServerResponse("Use not found");
		try {
			List<AuthorResponse> authorResponse = user.getAuthors();
			for (int i = 0; i < authorResponse.size(); i++) {
				Author author = new Author();
				String authorName = authorResponse.get(i).getFirstName() + " " + authorResponse.get(i).getLastName();
				author.setFirstname(authorResponse.get(i).getFirstName());
				author.setLastname(authorResponse.get(i).getLastName());
				author.setBiography(authorResponse.get(i).getBiography());
				author.setDate(authorResponse.get(i).getDate());
				author.setHasSynchronized(1);
				author.setImage(Upload.convertAndSaveImage(authorResponse.get(i).getCoverBytes(),
						authorResponse.get(i).getFirstName() + authorResponse.get(i).getLastName(), "authors"));
				authorService.addAuthor(author);
				List<BookResponse> bookResponse = authorResponse.get(i).getBooks();
				for (int j = 0; j < bookResponse.size(); j++) {
					long authorId = authorService.getAllAuthors().get(authorService.getAllAuthors().size() - 1).getId();
					String bookTitle = bookResponse.get(j).getTitle();
					Book book = new Book();
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
					book.setImage(Upload.convertAndSaveImage(bookResponse.get(j).getCoverBytes(),
							bookTitle, "books"));
					bookService.addBook(book);
					addAuthorBooksLinks(authorId);
					List<CitationResponse> citationResponse = bookResponse.get(i).getCitations();
					for (int z = 0; z < citationResponse.size(); z++) {
						long bookId = bookService.getAllBook().get(bookService.getAllBook().size() - 1).getId();
						Citation citation = new Citation();
						citation.setAuthor(authorName);
						citation.setAuthor_id(authorId);
						citation.setBook(bookTitle);
						citation.setBook_id(bookId);
						citation.setHasSynchronized(1);
						citation.setCitation(citationResponse.get(z).getCitation());
						citation.setDate(citationResponse.get(z).getDate());
						citation.setLiked(citationResponse.get(z).getLiked());
						citationService.addCitation(citation);
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
			List<Author> allAuthors = authorService.getAllAuthors();
			List<AuthorResponse> authors = new ArrayList<>();
			for (int i = 0; i < allAuthors.size(); i++) {
				AuthorResponse author = new AuthorResponse();
				author.setId(allAuthors.get(i).getId());
				author.setFirstName(allAuthors.get(i).getFirstname());
				author.setLastName(allAuthors.get(i).getLastname());
				author.setCoverBytes(getBytesFromFile(new File(
						(allAuthors.get(i).getImage() != null) ? allAuthors.get(i).getImage() : DEFAULT_IMAGE)));
				author.setBiography(allAuthors.get(i).getBiography());
				author.setDate(allAuthors.get(i).getDate());
				author.setHasSynchronized(1);

				List<Book> allBooks = bookService.getBookByAuthorId(author.getId(), 0);
				List<BookResponse> books = new ArrayList<>();

				for (int j = 0; j < allBooks.size(); j++) {
					BookResponse book = new BookResponse();
					book.setId(allBooks.get(j).getId());
					book.setTitle(allBooks.get(j).getTitle());
					book.setCoverBytes(getBytesFromFile(new File(
							(allBooks.get(j).getImage() != null) ? allBooks.get(j).getImage() : DEFAULT_IMAGE)));
					book.setDescription(allBooks.get(j).getDescription());
					book.setGenre(allBooks.get(j).getGenre());
					book.setInList(allBooks.get(j).getInList());
					book.setWasRead(allBooks.get(j).getStatus());
					book.setSeries(allBooks.get(j).getSeries());
					book.setNumSeries(allBooks.get(j).getSeriesNum());
					book.setHasSynchronized(1);
					book.setRating(allBooks.get(j).getRating());

					List<Citation> citationByBook = citationService.getCitationByBookId(book.getId(), 0);
					List<CitationResponse> citations = new ArrayList<>();
					for (int z = 0; z < citationByBook.size(); z++) {
						CitationResponse citation = new CitationResponse();
						citation.setId(citationByBook.get(z).getId());
						citation.setCitation(citationByBook.get(z).getCitation());
						citation.setLiked(citationByBook.get(z).getLiked());
						citation.setDate(citationByBook.get(z).getDate());
						citations.add(citation);
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

			// byteImage = new String(bytes, "UTF-8");

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
