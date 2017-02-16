package by.ansgar.navigation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link_book_citations")
public class LinkBookCitations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@ManyToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	@ManyToOne
	@JoinColumn(name = "citation_id", referencedColumnName = "id")
	private Citation citation;

	public LinkBookCitations() {

	}

	public LinkBookCitations(long id, Book book, Citation citation) {
		super();
		this.id = id;
		this.book = book;
		this.citation = citation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Citation getCitation() {
		return citation;
	}

	public void setCitation(Citation citation) {
		this.citation = citation;
	}

}
