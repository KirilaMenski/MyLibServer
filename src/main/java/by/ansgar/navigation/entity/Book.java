package by.ansgar.navigation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "image")
	private String image;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "rating")
	private int rating;
	@Column(name = "author")
	private String author;
	@Column(name = "author_id")
	private long author_id;
	@Column(name="status")
	private int status;
	@Column(name="in_list")
	private int inList;

	public Book() {

	}

	public Book(long id, String image, String title, String description,
			int rating, String author, long author_id, int status, int inList) {
		this.id = id;
		this.image = image;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.author = author;
		this.author_id = author_id;
		this.status = status;
		this.inList = inList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public int getInList() {
		return inList;
	}

	public void setInList(int inList) {
		this.inList = inList;
	}

}
