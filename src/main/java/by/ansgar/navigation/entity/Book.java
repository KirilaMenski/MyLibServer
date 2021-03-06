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
	@Column(name = "uuid")
	private String uuid;
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
	@Column(name = "status")
	private int status;
	@Column(name = "in_list")
	private int inList;
	@Column(name = "genre")
	private String genre;
	@Column(name = "series")
	private String series;
	@Column(name = "seriesNum")
	private int seriesNum;
	@Column(name="has_synchronized")
	private int hasSynchronized;

	public Book() {

	}

	public Book(long id, String uuid, String image, String title, String description, int rating, String author, long author_id,
			int status, int inList, String genre, String series, int seriesNum, int hasSynchronized) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.image = image;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.author = author;
		this.author_id = author_id;
		this.status = status;
		this.inList = inList;
		this.genre = genre;
		this.series = series;
		this.seriesNum = seriesNum;
		this.hasSynchronized = hasSynchronized;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getInList() {
		return inList;
	}

	public void setInList(int inList) {
		this.inList = inList;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public int getSeriesNum() {
		return seriesNum;
	}

	public void setSeriesNum(int seriesNum) {
		this.seriesNum = seriesNum;
	}

	public int getHasSynchronized() {
		return hasSynchronized;
	}

	public void setHasSynchronized(int hasSynchronized) {
		this.hasSynchronized = hasSynchronized;
	}

	

}
