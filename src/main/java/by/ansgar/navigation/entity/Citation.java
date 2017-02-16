package by.ansgar.navigation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "citations")
public class Citation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "citation")
	private String citation;
	@Column(name = "date")
	private String date;
	@Column(name="author")
	private String author;
	@Column(name="author_id")
	private int author_id;
	@Column(name="book")
	private String book;
	@Column(name="book_id")
	private int book_id;
	@Column(name="liked")
	private int liked;
	
	public Citation() {

	}

	public Citation(long id, String citation, String date, String author, int author_id, String book, int book_id, int liked) {
		super();
		this.id = id;
		this.citation = citation;
		this.date = date;
		this.author = author;
		this.author_id = author_id;
		this.book = book;
		this.book_id = book_id;
		this.liked = liked;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

}
