package rest_assured.payload.response.error;

public class UnauthorizedResponse {
    private String message;

    public UnauthorizedResponse() {
    }

    public UnauthorizedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
