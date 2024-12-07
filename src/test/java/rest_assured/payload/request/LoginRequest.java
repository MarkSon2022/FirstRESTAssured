package rest_assured.payload.request;

import rest_assured.utils.LogUtils;

public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        setUsername(username); // Sử dụng setter để kiểm tra
        setPassword(password); // Sử dụng setter để kiểm tra
    }

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    // Setter với validate
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: username cannot be empty");
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (username.length() < 3 || username.length() > 20) {
            LogUtils.error("Validation ERROR: Username must be between 3 and 20");
            throw new IllegalArgumentException("Username must be between 3 and 20");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            LogUtils.error("Validation ERROR: Password cannot be empty");
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 6 || password.length() > 50) {
            LogUtils.error("Validation ERROR: Password must be between 6 and 50");
            throw new IllegalArgumentException("Password must be between 6 and 50");
        }
        this.password = password;
    }
}
