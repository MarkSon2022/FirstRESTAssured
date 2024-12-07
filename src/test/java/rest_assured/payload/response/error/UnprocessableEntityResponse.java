package rest_assured.payload.response.error;

public class UnprocessableEntityResponse {
    private String message;
    private ErrorEntity errors;

    public UnprocessableEntityResponse() {
    }

    public UnprocessableEntityResponse(String message, ErrorEntity errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorEntity getError() {
        return errors;
    }

    public void setError(ErrorEntity error) {
        this.errors = error;
    }
}
