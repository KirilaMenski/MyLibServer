package by.ansgar.navigation.entity.response;

public class CitationResponse {
	
	private long id;
	private String uuid;
	private String citation;
	private int liked;
	private String date;
	private int hasSynchronized;
	
	public CitationResponse(){
		
	}

	public CitationResponse(long id, String uuid, String citation, int liked, String date, int hasSynchronized) {
		super();
		this.id = id;
		this.citation = citation;
		this.liked = liked;
		this.date = date;
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

	public int getHasSynchronized() {
		return hasSynchronized;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
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
	
	

}
