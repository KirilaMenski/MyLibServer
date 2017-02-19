package by.ansgar.navigation.entity.response;

public class ServerResponse {
	
	private String message;

    public ServerResponse() {

    }

    public ServerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
