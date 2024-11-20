package rest_assured.payload.response;

public class UpdateUserResponse {

    private String message;
    private UpdateUser response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpdateUser getResponse() {
        return response;
    }

    public void setResponse(UpdateUser response) {
        this.response = response;
    }
}
