package by.ansgar.navigation.entity.response;

import java.util.List;

public class BookResponse {

	private long id;
	String uuid;
	private String title;
	private String description;
	private String genre;
	private String coverBytes;
	private String series;
	private int numSeries;
	private int year;
	private int inList;
	private int rating;
	private int wasRead;
	private int hasSynchronized;
	private List<CitationResponse> citations;
	
	public BookResponse(){
		
	}

	public BookResponse(long id, String uuid, String title, String description, String genre, String coverBytes, String series,
			int numSeries, int year, int inList, int rating, int wasRead, int hasSynchronized,
			List<CitationResponse> citations) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.coverBytes = coverBytes;
		this.series = series;
		this.numSeries = numSeries;
		this.year = year;
		this.inList = inList;
		this.rating = rating;
		this.wasRead = wasRead;
		this.hasSynchronized = hasSynchronized;
		this.citations = citations;
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

	public int getHasSynchronized() {
		return hasSynchronized;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCoverBytes() {
		return coverBytes;
	}

	public void setCoverBytes(String coverBytes) {
		this.coverBytes = coverBytes;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public int getNumSeries() {
		return numSeries;
	}

	public void setNumSeries(int numSeries) {
		this.numSeries = numSeries;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getInList() {
		return inList;
	}

	public void setInList(int inList) {
		this.inList = inList;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getWasRead() {
		return wasRead;
	}

	public void setWasRead(int wasRead) {
		this.wasRead = wasRead;
	}

	public int isHasSynchronized() {
		return hasSynchronized;
	}

	public void setHasSynchronized(int hasSynchronized) {
		this.hasSynchronized = hasSynchronized;
	}

	public List<CitationResponse> getCitations() {
		return citations;
	}

	public void setCitations(List<CitationResponse> citations) {
		this.citations = citations;
	}
	
	
}
