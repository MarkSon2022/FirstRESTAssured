package rest_assured.payload.response;

public class UserResponse {
    private String message;
    private User response;

    public UserResponse() {
    }

    public UserResponse(String message, User response) {
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getResponse() {
        return response;
    }

    public void setResponse(User response) {
        this.response = response;
    }
}
