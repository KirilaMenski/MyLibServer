package by.ansgar.navigation.entity.response;

import java.util.List;

public class AuthorResponse {

	private long id;
	private String coverBytes;
	private String firstName;
	private String lastName;
	private String biography;
	private String date;
	private int hasSynchronized;
	private List<BookResponse> books;
	
	public AuthorResponse(){
		
	}

	public AuthorResponse(long id, String coverBytes, String firstName, String lastName, String biography, String date,
			int hasSynchronized, List<BookResponse> books) {
		super();
		this.id = id;
		this.coverBytes = coverBytes;
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
		this.date = date;
		this.hasSynchronized = hasSynchronized;
		this.books = books;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCoverBytes() {
		return coverBytes;
	}

	public void setCoverBytes(String coverBytes) {
		this.coverBytes = coverBytes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int isHasSynchronized() {
		return hasSynchronized;
	}

	public void setHasSynchronized(int hasSynchronized) {
		this.hasSynchronized = hasSynchronized;
	}

	public List<BookResponse> getBooks() {
		return books;
	}

	public void setBooks(List<BookResponse> books) {
		this.books = books;
	}
	
	
	
}
