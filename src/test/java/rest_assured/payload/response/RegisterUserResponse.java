package rest_assured.payload.response;

public class RegisterUserResponse {
    private String message;
    private RegisterUser response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterUser getResponse() {
        return response;
    }

    public void setResponse(RegisterUser response) {
        this.response = response;
    }
}