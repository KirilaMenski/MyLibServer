package by.ansgar.navigation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reading_list")
public class ReadingList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="book_title")
	private String book;
	@Column(name="book_id")
	private long book_id;
	@Column(name="author")
	private String author;
	@Column(name="author_id")
	private long author_id;
	@Column(name="pick")
	private int pick;
	
	public ReadingList(){
		
	}
	
	public ReadingList(long id, String book, long book_id, String author,
			long author_id, int pick) {
		super();
		this.id = id;
		this.book = book;
		this.book_id = book_id;
		this.author = author;
		this.author_id = author_id;
		this.pick = pick;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(long author_id) {
		this.author_id = author_id;
	}

	public int getPick() {
		return pick;
	}

	public void setPick(int pick) {
		this.pick = pick;
	}
	
	

}
