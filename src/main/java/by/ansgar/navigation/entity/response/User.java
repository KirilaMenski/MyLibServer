package by.ansgar.navigation.entity.response;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	private long id;
	private String firstName;
	private String lastName;
	private String coverBytes;
	private String email;
	private String password;
	private int hasSynchronized;
	private List<AuthorResponse> authors;

	public User() {

	}

	public User(long id, String firstName, String lastName, String coverBytes, String email, String password,
			int hasSynchronized, List<AuthorResponse> authors) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.coverBytes = coverBytes;
		this.email = email;
		this.password = password;
		this.hasSynchronized = hasSynchronized;
		this.authors = authors;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCoverBytes() {
		return coverBytes;
	}

	public void setCoverBytes(String coverBytes) {
		this.coverBytes = coverBytes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int isHasSynchronized() {
		return hasSynchronized;
	}

	public void setHasSynchronized(int hasSynchronized) {
		this.hasSynchronized = hasSynchronized;
	}

	public List<AuthorResponse> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorResponse> authors) {
		this.authors = authors;
	}

}
