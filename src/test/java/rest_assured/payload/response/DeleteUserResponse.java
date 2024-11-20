package rest_assured.payload.response;

public class DeleteUserResponse {

    private String message;
    private DeleteUser response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeleteUser getResponse() {
        return response;
    }

    public void setResponse(DeleteUser response) {
        this.response = response;
    }
}
